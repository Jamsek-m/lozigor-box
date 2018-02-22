package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.entities.exceptions.EmailZeObstajaException;
import com.mjamsek.lozigorbox.entities.exceptions.GesliSeNeUjemataException;
import com.mjamsek.lozigorbox.entities.requests.UporabnikUpdateRequest;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import com.mjamsek.lozigorbox.repositories.UporabnikRepository;
import com.mjamsek.lozigorbox.services.UporabnikService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UporabnikServiceImpl implements UporabnikService {
	
	@Inject
	private UporabnikRepository uporabnikRepository;
	
	@Inject
	private BCryptPasswordEncoder bCrypt;
	
	@Override
	public Uporabnik poisciZId(long id) {
		return uporabnikRepository.findOne(id);
	}
	
	@Override
	public Uporabnik poisciZEmailom(String email) {
		return uporabnikRepository.findByEmail(email);
	}
	
	@Override
	public List<Uporabnik> poisciVse() {
		return uporabnikRepository.findAll();
	}
	
	@Override
	public void shraniUporabnika(Uporabnik uporabnik) {
		uporabnikRepository.save(uporabnik);
	}
	
	@Override
	public void posodobiUporabnika(Uporabnik header, UporabnikUpdateRequest body) throws GesliSeNeUjemataException, EmailZeObstajaException {
		switch (validirajGeslo(body.geslo1, body.geslo2)) {
			case ERR:
				throw new GesliSeNeUjemataException();
			case OK_PRAZNO:
				//posodobi brez gesla
				if(!header.getEmail().equals(body.email)) {
					if(this.uporabnikRepository.countByEmail(body.email) != 0) {
						throw new EmailZeObstajaException();
					}
					header.setEmail(body.email);
				}
				header.setUporabniskoIme(body.uporabniskoIme);
				this.uporabnikRepository.save(header);
				break;
			case OK_UJEMA:
				//posodobi z geslom
				if(!header.getEmail().equals(body.email)) {
					if(this.uporabnikRepository.countByEmail(body.email) != 0) {
						throw new EmailZeObstajaException();
					}
					header.setEmail(body.email);
				}
				header.setUporabniskoIme(body.uporabniskoIme);
				header.setGeslo(bCrypt.encode(body.geslo1));
				this.uporabnikRepository.save(header);
				break;
		}
	}
	
	private Gesla validirajGeslo(@NotNull String geslo1, @NotNull String geslo2) {
		if(geslo1.isEmpty() ^ geslo2.isEmpty()) {
			return Gesla.ERR;
		} else {
			if(geslo1.isEmpty()) {
				return Gesla.OK_PRAZNO;
			} else {
				if(geslo1.equals(geslo2)) {
					return Gesla.OK_UJEMA;
				} else {
					return Gesla.ERR;
				}
			}
		}
	}
	
}

enum Gesla {
	OK_PRAZNO,
	OK_UJEMA,
	ERR
}
