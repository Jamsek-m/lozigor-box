package com.mjamsek.lozigorbox.services;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import com.mjamsek.lozigorbox.entities.requests.Direktorij;

import java.util.List;

public interface MenuItemService {
	
	public final String ROOT_ID = "ROOT_DIR";
	
	public MenuItem pridobiKorenskiElement();
	
	public List<MenuItem> pridobiPrvoStran();
	
	public List<MenuItem> pridobiOtrokeElementa(long id);
	
	public void dodajDirektorij(Direktorij dir);
	
	public void dodajDatoteko(Datoteka dat, long parent);
	
}
