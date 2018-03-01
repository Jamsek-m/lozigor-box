package com.mjamsek.lozigorbox.controllers.v1;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.security.anotacije.ImaVlogo;
import com.mjamsek.lozigorbox.security.config.Vloge;
import com.mjamsek.lozigorbox.services.DatotekaService;
import com.mjamsek.lozigorbox.services.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
	
	@Inject
	private FileService fileService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Resource> posredujDatoteko(@PathVariable("id") long id) {
		Datoteka datoteka = datotekaService.pridobiDatoteko(id);
		Resource file = fileService.pridobiKotResurs(datoteka.getLokacija());
		String attachment = "attachment; filename=\"" + file.getFilename() + "\"";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, attachment).body(file);
	}
	
	@PostMapping(value = {"", "/"}, headers = "Content-Type=multipart/form-data")
	@ImaVlogo({Vloge.ADMIN, Vloge.MOD})
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
