package com.mjamsek.lozigorbox.security.interceptorji;

import com.mjamsek.lozigorbox.entities.exceptions.NiPravicException;
import com.mjamsek.lozigorbox.services.ZetonService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import static com.mjamsek.lozigorbox.security.config.SecurityKonstante.*;

@Aspect
public class JeAvtenticiranInterceptor {
	
	@Inject
	private ZetonService zetonService;
	
	@Around("@annotation(com.mjamsek.lozigorbox.security.anotacije.JeAvtenticiran)")
	public Object preveriAvtentikacijo(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String authorizationField = req.getHeader(HEADER_STRING);
		if(authorizationField == null) {
			throw new NiPravicException();
		}
		
		String token = authorizationField.replace(TOKEN_PREFIX, "");
		
		if(zetonService.validirajZeton(token)) {
			return joinPoint.proceed();
		} else {
			throw new NiPravicException();
		}
	}
}
