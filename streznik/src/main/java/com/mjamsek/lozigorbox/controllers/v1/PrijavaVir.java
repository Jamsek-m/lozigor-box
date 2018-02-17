package com.mjamsek.lozigorbox.controllers.v1;

import com.mjamsek.lozigorbox.entities.exceptions.NapacnaPrijavaException;
import com.mjamsek.lozigorbox.entities.requests.PrijavaRequest;
import com.mjamsek.lozigorbox.entities.responses.PrijavaResponse;
import com.mjamsek.lozigorbox.security.PrijavaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
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
