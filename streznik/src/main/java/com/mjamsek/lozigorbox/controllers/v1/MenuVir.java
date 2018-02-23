package com.mjamsek.lozigorbox.controllers.v1;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import com.mjamsek.lozigorbox.entities.requests.Direktorij;
import com.mjamsek.lozigorbox.entities.responses.MenuItemResponse;
import com.mjamsek.lozigorbox.services.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/menu")
public class MenuVir {
	
	@Inject
	private MenuItemService menuItemService;
	
	@GetMapping("/root")
	public ResponseEntity<MenuItemResponse> posredujPrvoStranMenuja() {
		MenuItemResponse items = menuItemService.pridobiPrvoStran();
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/dir/{id}")
	public ResponseEntity<MenuItemResponse> posredujDirektorij(@PathVariable("id") long id) {
		MenuItemResponse items = menuItemService.pridobiOtrokeElementa(id);
		return ResponseEntity.ok(items);
	}
	
	@GetMapping("/dir")
	public ResponseEntity<List<MenuItem>> poisciZPodanimKljucem(@RequestParam("query") String query) {
		return ResponseEntity.ok(this.menuItemService.poisciZQueryjem(query));
	}
	
	@PostMapping("/dir")
	public ResponseEntity kreirajDirektorij(@RequestBody Direktorij dir) {
		menuItemService.dodajDirektorij(dir);
		URI uri = null;
		try {
			uri = new URI("#");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(uri).body(dir);
	}
	
}
