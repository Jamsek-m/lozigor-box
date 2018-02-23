package com.mjamsek.lozigorbox.entities.responses;

import com.mjamsek.lozigorbox.entities.menu.MenuItem;

import java.util.List;

public class MenuItemResponse {
	
	public long id;
	
	public String ime;
	
	public long parent;
	
	public List<MenuItem> items;
	
	public MenuItemResponse(long id, long parent, String ime, List<MenuItem> items) {
		this.id = id;
		this.ime = ime;
		this.parent = parent;
		this.items = items;
	}
}
