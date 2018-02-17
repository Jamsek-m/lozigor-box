package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.entities.uporabnik.Vloga;
import com.mjamsek.lozigorbox.repositories.VlogaRepository;
import com.mjamsek.lozigorbox.services.VlogaService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class VlogaServiceImpl implements VlogaService {
	
	@Inject
	private VlogaRepository vlogaRepository;
	
	@Override
	public Vloga poisciZSifro(String sifra) {
		return vlogaRepository.findBySifra(sifra);
	}
}
