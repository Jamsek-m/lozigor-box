package com.mjamsek.lozigorbox.security;

import com.mjamsek.lozigorbox.entities.exceptions.NapacnaPrijavaException;
import com.mjamsek.lozigorbox.entities.requests.PrijavaRequest;

public interface PrijavaService {
	
	/**
	* Metoda za prijavo uporabnika
	*
	* @param req Zahteva s polji 'email' in 'geslo'
	* @return Vrne String v katerem je zgeneriran JWT zeton
	*/
	public String prijaviUporabnika(PrijavaRequest req) throws NapacnaPrijavaException;
	
}
