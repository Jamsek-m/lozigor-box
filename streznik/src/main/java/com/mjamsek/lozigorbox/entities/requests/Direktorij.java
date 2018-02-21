package com.mjamsek.lozigorbox.entities.requests;

public class Direktorij {
	
	public String ime;
	
	public long parent;
	
	public Direktorij() {
	}
	
	public Direktorij(String ime, long parent) {
		this.ime = ime;
		this.parent = parent;
	}
}
