package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import com.mjamsek.lozigorbox.repositories.UporabnikRepository;
import com.mjamsek.lozigorbox.services.UporabnikService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UporabnikServiceImpl implements UporabnikService {
	
	@Inject
	private UporabnikRepository uporabnikRepository;
	
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
}
