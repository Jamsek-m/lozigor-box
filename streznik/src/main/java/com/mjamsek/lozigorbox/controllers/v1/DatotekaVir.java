package com.mjamsek.lozigorbox.controllers.v1;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.services.DatotekaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/datoteke")
public class DatotekaVir {
	
	@Inject
	private DatotekaService datotekaService;
	
	@PostMapping({"", "/"})
	public ResponseEntity shraniDatoteko(
			@RequestParam("files")MultipartFile[] files,
			@RequestParam("parent") long parent
			) {
		datotekaService.shraniDatoteke(files, parent);
		URI uri = null;
		try {
			uri = new URI("#");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(uri).body(null);
	}
}
