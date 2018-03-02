package com.mjamsek.lozigorbox.services;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import com.mjamsek.lozigorbox.entities.requests.Direktorij;
import com.mjamsek.lozigorbox.entities.responses.MenuItemResponse;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;

import java.util.List;

public interface MenuItemService {
	
	public final String ROOT_ID = "ROOT_DIR";
	
	public MenuItem pridobiKorenskiElement();
	
	public MenuItemResponse pridobiPrvoStran(Uporabnik uporabnik);
	
	public MenuItemResponse pridobiOtrokeElementa(long id, Uporabnik uporabnik);
	
	public List<MenuItem> poisciZQueryjem(String query, Uporabnik uporabnik);
	
	public void dodajDirektorij(Direktorij dir);
	
	public void dodajDatoteko(Datoteka dat, long parent);
	
}
