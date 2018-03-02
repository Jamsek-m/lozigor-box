package com.mjamsek.lozigorbox.services;

import com.mjamsek.lozigorbox.entities.exceptions.NiPravicException;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import javax.servlet.http.HttpServletRequest;

public interface ZetonService {
	
	public String generirajZeton(Uporabnik uporabnik);
	
	public boolean validirajZeton(String zeton);
	
	public Claims validirajZetonInVrniPodatke(String zeton);
	
	public Uporabnik pridobiUporabnikaIzZetona(HttpServletRequest req);
	
}
