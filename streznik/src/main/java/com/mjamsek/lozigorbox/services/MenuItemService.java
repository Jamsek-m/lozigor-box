package com.mjamsek.lozigorbox.services;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import com.mjamsek.lozigorbox.entities.requests.Direktorij;
import com.mjamsek.lozigorbox.entities.responses.MenuItemResponse;

import java.util.List;

public interface MenuItemService {
	
	public final String ROOT_ID = "ROOT_DIR";
	
	public MenuItem pridobiKorenskiElement();
	
	public MenuItemResponse pridobiPrvoStran();
	
	public MenuItemResponse pridobiZavarovanoPrvoStran(long parent, long uporabnik);
	
	public MenuItemResponse pridobiOtrokeElementa(long id);
	
	public List<MenuItem> poisciZQueryjem(String query);
	
	public void dodajDirektorij(Direktorij dir);
	
	public void dodajDatoteko(Datoteka dat, long parent);
	
}
