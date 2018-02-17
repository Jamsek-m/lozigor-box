package com.mjamsek.lozigorbox.entities.responses;

import java.io.Serializable;

public class PrijavaResponse implements Serializable {
	
	public String zeton;
	
	public String sporocilo;
	
	public PrijavaResponse() {
	}
	
	public PrijavaResponse(String zeton, String sporocilo) {
		this.zeton = zeton;
		this.sporocilo = sporocilo;
	}
}
