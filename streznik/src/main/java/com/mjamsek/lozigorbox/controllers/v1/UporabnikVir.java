package com.mjamsek.lozigorbox.controllers.v1;

import com.mjamsek.lozigorbox.entities.exceptions.EmailZeObstajaException;
import com.mjamsek.lozigorbox.entities.exceptions.GesliSeNeUjemataException;
import com.mjamsek.lozigorbox.entities.exceptions.NiPravicException;
import com.mjamsek.lozigorbox.entities.requests.UporabnikUpdateRequest;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import com.mjamsek.lozigorbox.security.anotacije.ImaVlogo;
import com.mjamsek.lozigorbox.security.anotacije.JeAvtenticiran;
import com.mjamsek.lozigorbox.security.config.Vloge;
import com.mjamsek.lozigorbox.services.UporabnikService;
import com.mjamsek.lozigorbox.services.ZetonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/uporabniki")
public class UporabnikVir {
	
	@Inject
	private ZetonService zetonService;
	
	@Inject
	private UporabnikService uporabnikService;
	
	@GetMapping("/profil")
	@JeAvtenticiran
	public ResponseEntity<Uporabnik> vrniSvojePodrobnosti(HttpServletRequest req) throws NiPravicException {
		Uporabnik uporabnik = this.zetonService.pridobiUporabnikaIzZetona(req);
		return ResponseEntity.ok(uporabnik);
	}
	
	@PutMapping("/profil")
	@JeAvtenticiran
	public ResponseEntity posodobiSvojePodrobnosti(
			@RequestBody UporabnikUpdateRequest requestBody,
			HttpServletRequest req) throws NiPravicException, GesliSeNeUjemataException, EmailZeObstajaException {
		Uporabnik uporabnik = this.zetonService.pridobiUporabnikaIzZetona(req);
		this.uporabnikService.posodobiUporabnika(uporabnik, requestBody);
		return ResponseEntity.noContent().build();
	}
	
	// PRIMERI UPORABE AVTENTIKACIJE:
	/*@GetMapping("/admin")
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
	*/
}

