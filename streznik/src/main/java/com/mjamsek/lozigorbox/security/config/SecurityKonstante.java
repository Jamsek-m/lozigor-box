package com.mjamsek.lozigorbox.security.config;

import java.util.Calendar;
import java.util.Date;

public class SecurityKonstante {
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public static Date vrniExpirationDatum(int secondsFromNow) {
		Date trenutniCas = new Date();
		
		Calendar koledar = Calendar.getInstance();
		koledar.setTime(trenutniCas);
		
		koledar.add(Calendar.SECOND, secondsFromNow);
		
		return koledar.getTime();
	}
}
