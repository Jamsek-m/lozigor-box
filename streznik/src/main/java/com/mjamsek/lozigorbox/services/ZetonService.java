package com.mjamsek.lozigorbox.services;

import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface ZetonService {
	
	public String generirajZeton(Uporabnik uporabnik);
	
	public boolean validirajZeton(String zeton);
	
	public Claims validirajZetonInVrniPodatke(String zeton);
	
}
