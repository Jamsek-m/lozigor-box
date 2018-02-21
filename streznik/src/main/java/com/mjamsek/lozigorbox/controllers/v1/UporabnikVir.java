package com.mjamsek.lozigorbox.controllers.v1;

import com.mjamsek.lozigorbox.entities.exceptions.NiPravicException;
import com.mjamsek.lozigorbox.security.anotacije.ImaVlogo;
import com.mjamsek.lozigorbox.security.anotacije.JeAvtenticiran;
import com.mjamsek.lozigorbox.security.config.Vloge;
import com.mjamsek.lozigorbox.services.ZetonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UporabnikVir {
	
	@Inject
	private ZetonService zetonService;
	
	@GetMapping("/admin")
	@ImaVlogo({Vloge.ADMIN})
	public ResponseEntity<String> adminPage(HttpServletRequest req) throws NiPravicException{
		return ResponseEntity.ok().body("To je stran samo za ADMINE!\nPozdravljen, " + zetonService.pridobiUporabnikaIzZetona(req).getUporabniskoIme());
	}
	
	@GetMapping("/mod")
	@ImaVlogo({Vloge.ADMIN, Vloge.MOD})
	public ResponseEntity<String> modPage() {
		return ResponseEntity.ok().body("To je stran samo za MODE in ADMINE!");
	}
	
	@GetMapping("/user")
	@JeAvtenticiran
	public ResponseEntity<String> userPage() {
		return ResponseEntity.ok().body("To je stran samo za AVTENTICIRANE UPORABNIKE!");
	}
	
	@GetMapping("/public")
	public ResponseEntity<String> publicPage() {
		return ResponseEntity.ok().body("To je stran za VSE!");
	}
	
}

