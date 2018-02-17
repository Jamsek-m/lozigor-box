package com.mjamsek.lozigorbox.security.config;

public enum Vloge {
	ADMIN("ADMIN"),
	MOD("MOD"),
	USER("USER");
	
	private String sifra;
	
	Vloge(String sifra) {
		this.sifra = sifra;
	}
	
	public String sifra() {
		return this.sifra;
	}
}
