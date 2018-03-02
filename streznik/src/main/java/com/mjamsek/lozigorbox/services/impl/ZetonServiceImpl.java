package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.entities.exceptions.NiPravicException;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import com.mjamsek.lozigorbox.services.UporabnikService;
import com.mjamsek.lozigorbox.services.ZetonService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static com.mjamsek.lozigorbox.security.config.SecurityKonstante.HEADER_STRING;
import static com.mjamsek.lozigorbox.security.config.SecurityKonstante.TOKEN_PREFIX;
import static com.mjamsek.lozigorbox.security.config.SecurityKonstante.vrniExpirationDatum;

@Service
public class ZetonServiceImpl implements ZetonService {
	
	@Value("${politika.prijava.veljavnost}")
	private Integer secondsFromNow;
	
	@Value("${politika.jwt-token.secret-key}")
	private String secretKey;
	
	@Value("${politika.jwt-token.title}")
	private String JWTSubject;
	
	@Inject
	private UporabnikService uporabnikService;
	
	@Override
	public String generirajZeton(Uporabnik uporabnik) {
		try {
			Date expirationDate = vrniExpirationDatum(secondsFromNow); // 1 leto
			
			String token = Jwts.builder()
					.setSubject(JWTSubject)
					.setIssuedAt(new Date())
					.setExpiration(expirationDate)
					.claim("uporabnik_ime", uporabnik.getUporabniskoIme())
					.claim("vloge", uporabnik.getVloge())
					.claim("uporabnik_id", uporabnik.getId())
					.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
					.compact();
			return token;
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean validirajZeton(String zeton) throws MissingClaimException, SignatureException {
		try {
			Jws<Claims> claims = Jwts.parser()
					.requireSubject(JWTSubject)
					.setSigningKey(secretKey.getBytes("UTF-8"))
					.parseClaimsJws(zeton);
			return true;
		} catch (UnsupportedEncodingException e4) {
			e4.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Claims validirajZetonInVrniPodatke(String zeton) {
		try {
			Claims claims = Jwts.parser()
					.requireSubject(JWTSubject)
					.setSigningKey(secretKey.getBytes("UTF-8"))
					.parseClaimsJws(zeton)
					.getBody();
			return claims;
		} catch (UnsupportedEncodingException e4) {
			e4.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Uporabnik pridobiUporabnikaIzZetona(HttpServletRequest req) {
		String authorizationField = req.getHeader(HEADER_STRING);
		if(authorizationField == null) {
			return null;
		}
		
		String token = authorizationField.replace(TOKEN_PREFIX, "");
		
		Claims podatkiZeton = this.validirajZetonInVrniPodatke(token);
		
		long idUporabnika = Long.parseLong(podatkiZeton.get("uporabnik_id").toString());
		Uporabnik uporabnik = uporabnikService.poisciZId(idUporabnika);
		
		return uporabnik;
	}
}
