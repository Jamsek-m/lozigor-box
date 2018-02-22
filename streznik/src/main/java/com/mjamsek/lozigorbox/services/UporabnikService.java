package com.mjamsek.lozigorbox.services;

import com.mjamsek.lozigorbox.entities.exceptions.EmailZeObstajaException;
import com.mjamsek.lozigorbox.entities.exceptions.GesliSeNeUjemataException;
import com.mjamsek.lozigorbox.entities.requests.UporabnikUpdateRequest;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;

import java.util.List;

public interface UporabnikService {
	
	public Uporabnik poisciZId(long id);
	
	public Uporabnik poisciZEmailom(String email);
	
	public List<Uporabnik> poisciVse();
	
	public void shraniUporabnika(Uporabnik uporabnik);
	
	public void posodobiUporabnika(Uporabnik header, UporabnikUpdateRequest body) throws GesliSeNeUjemataException, EmailZeObstajaException;
}
