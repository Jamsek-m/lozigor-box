package com.mjamsek.lozigorbox.entities.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {SignatureException.class})
	protected ResponseEntity<Object> neveljavenZeton(SignatureException ex, WebRequest req) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ExceptionResponse res = new ExceptionResponse(ex.getMessage(), status.value());
		
		return handleExceptionInternal(ex, res, new HttpHeaders(), status, req);
	}
	
	@ExceptionHandler(value = {ExpiredJwtException.class})
	protected  ResponseEntity<Object> expiredZeton(ExpiredJwtException ex, WebRequest req) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ExceptionResponse res = new ExceptionResponse(ex.getMessage(), status.value());
		
		return handleExceptionInternal(ex, res, new HttpHeaders(), status, req);
	}
	
	@ExceptionHandler(value = {NapacnaPrijavaException.class})
	protected  ResponseEntity<Object> expiredZeton(NapacnaPrijavaException ex, WebRequest req) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ExceptionResponse res = new ExceptionResponse(ex.getMessage(), status.value());
		
		return handleExceptionInternal(ex, res, new HttpHeaders(), status, req);
	}
	
	@ExceptionHandler(value = {NiPravicException.class})
	protected  ResponseEntity<Object> expiredZeton(NiPravicException ex, WebRequest req) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		ExceptionResponse res = new ExceptionResponse(ex.getMessage(), status.value());
		
		return handleExceptionInternal(ex, res, new HttpHeaders(), status, req);
	}

}
