package com.mjamsek.lozigorbox.controllers.v1;

import com.mjamsek.lozigorbox.security.anotacije.ImaVlogo;
import com.mjamsek.lozigorbox.security.anotacije.JeAvtenticiran;
import com.mjamsek.lozigorbox.security.config.Vloge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UporabnikVir {
	
	@GetMapping("/admin")
	@ImaVlogo({Vloge.ADMIN})
	public ResponseEntity<String> adminPage() {
		return ResponseEntity.ok().body("To je stran samo za ADMINE!");
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

