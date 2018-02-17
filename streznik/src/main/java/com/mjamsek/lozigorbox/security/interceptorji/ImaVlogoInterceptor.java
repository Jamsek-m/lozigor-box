package com.mjamsek.lozigorbox.security.interceptorji;

import com.mjamsek.lozigorbox.entities.exceptions.NiPravicException;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import com.mjamsek.lozigorbox.security.anotacije.ImaVlogo;
import com.mjamsek.lozigorbox.security.config.Vloge;
import com.mjamsek.lozigorbox.services.UporabnikService;
import com.mjamsek.lozigorbox.services.ZetonService;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import static com.mjamsek.lozigorbox.security.config.SecurityKonstante.*;

@Aspect
public class ImaVlogoInterceptor {
	
	@Inject
	private ZetonService zetonService;
	
	@Inject
	private UporabnikService uporabnikService;
	
	@Around("@annotation(com.mjamsek.lozigorbox.security.anotacije.ImaVlogo)")
	public Object preveriAvtentikacijoInVlogo(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String authorizationField = req.getHeader(HEADER_STRING);
		if(authorizationField == null) {
			throw new NiPravicException();
		}
		
		String token = authorizationField.replace(TOKEN_PREFIX, "");
		
		Claims podatkiZeton = zetonService.validirajZetonInVrniPodatke(token);
		
		long idUporabnika = Long.parseLong(podatkiZeton.get("uporabnik").toString());
		Uporabnik uporabnik = uporabnikService.poisciZId(idUporabnika);
		
		ImaVlogo anotacija = vrniAnotacijo(joinPoint);
		
		Vloge[] dovoljeneVloge = anotacija.value();
		
		boolean imaUstreznoVlogo = false;
		for(Vloge vloga : dovoljeneVloge) {
			if(uporabnik.imaVlogo(vloga.sifra())) {
				imaUstreznoVlogo = true;
				break;
			}
		}
		
		if(imaUstreznoVlogo) {
			return joinPoint.proceed();
		} else {
			throw new NiPravicException();
		}
	}
	
	private ImaVlogo vrniAnotacijo(ProceedingJoinPoint join) {
		return ((MethodSignature) join.getSignature()).getMethod().getAnnotation(ImaVlogo.class);
	}
	
}
