package com.mjamsek.lozigorbox.security;

import com.mjamsek.lozigorbox.entities.exceptions.NapacnaPrijavaException;
import com.mjamsek.lozigorbox.entities.requests.PrijavaRequest;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import com.mjamsek.lozigorbox.entities.uporabnik.Vloga;
import com.mjamsek.lozigorbox.services.UporabnikService;
import com.mjamsek.lozigorbox.services.VlogaService;
import com.mjamsek.lozigorbox.services.ZetonService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

@Service
public class PrijavaServiceImpl implements PrijavaService {
	
	@Inject
	private ZetonService zetonService;
	
	@Inject
	private UporabnikService uporabnikService;
	
	@Inject
	private BCryptPasswordEncoder bCrypt;
	
	@Override
	public String prijaviUporabnika(PrijavaRequest req) throws NapacnaPrijavaException {
		Uporabnik uporabnik = uporabnikService.poisciZEmailom(req.email);
		
		if(uporabnik == null) {
			throw new NapacnaPrijavaException();
		}
		
		if(bCrypt.matches(req.geslo, uporabnik.getGeslo())) {
			String zeton = zetonService.generirajZeton(uporabnik);
			return zeton;
		} else {
			throw new NapacnaPrijavaException();
		}
	}
}
