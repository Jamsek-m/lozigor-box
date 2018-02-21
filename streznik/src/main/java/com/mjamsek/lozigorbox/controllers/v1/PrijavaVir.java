package com.mjamsek.lozigorbox.controllers.v1;

import com.mjamsek.lozigorbox.entities.exceptions.NapacnaPrijavaException;
import com.mjamsek.lozigorbox.entities.requests.PrijavaRequest;
import com.mjamsek.lozigorbox.entities.responses.PrijavaResponse;
import com.mjamsek.lozigorbox.security.PrijavaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class PrijavaVir {

	@Inject
	private PrijavaService prijavaService;
	
	@PostMapping("/prijava")
	public ResponseEntity<PrijavaResponse> prijaviUporabnika(@RequestBody PrijavaRequest req)
			throws NapacnaPrijavaException {
		
		String token = prijavaService.prijaviUporabnika(req);
		
		PrijavaResponse res = new PrijavaResponse(token, "Prijava je bila uspesna!");
		
		return ResponseEntity.ok().body(res);
	}

}
