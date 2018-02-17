package com.mjamsek.lozigorbox.entities.requests;

import java.io.Serializable;

public class PrijavaRequest implements Serializable {
	
	public String email;
	
	public String geslo;
	
	public PrijavaRequest() {
	}
	
	public PrijavaRequest(String email, String geslo) {
		this.email = email;
		this.geslo = geslo;
	}
}
