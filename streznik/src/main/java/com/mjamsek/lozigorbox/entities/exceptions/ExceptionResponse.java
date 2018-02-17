package com.mjamsek.lozigorbox.entities.exceptions;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {
	
	public String napaka;
	
	public int status;
	
	public ExceptionResponse() {
	}
	
	public ExceptionResponse(String napaka, int status) {
		this.napaka = napaka;
		this.status = status;
	}
}
