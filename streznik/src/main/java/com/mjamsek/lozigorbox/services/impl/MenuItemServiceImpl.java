package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import com.mjamsek.lozigorbox.entities.menu.MenuItemType;
import com.mjamsek.lozigorbox.entities.requests.Direktorij;
import com.mjamsek.lozigorbox.repositories.MenuItemRepository;
import com.mjamsek.lozigorbox.services.MenuItemService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {
	
	@Inject
	private MenuItemRepository menuItemRepository;
	
	@Override
	public MenuItem pridobiKorenskiElement() {
		return menuItemRepository.findByIme(ROOT_ID);
	}
	
	@Override
	public List<MenuItem> pridobiPrvoStran() {
		return menuItemRepository.findAllByParent(pridobiKorenskiElement().getId());
	}
	
	@Override
	public List<MenuItem> pridobiOtrokeElementa(long id) {
		return menuItemRepository.findAllByParent(id);
	}
	
	@Override
	public void dodajDirektorij(Direktorij dir) {
		MenuItem item = new MenuItem();
		item.setParent(dir.parent);
		item.setFile(null);
		item.setNum_of_childs(0);
		item.setIme(dir.ime);
		item.setTip(MenuItemType.DIR);
		
		menuItemRepository.save(item);
	}
	
	@Override
	public void dodajDatoteko(Datoteka dat, long parent) {
		MenuItem item = new MenuItem();
		item.setParent(parent);
		item.setFile(dat);
		item.setNum_of_childs(0);
		item.setIme(null);
		item.setTip(MenuItemType.FILE);
		
		menuItemRepository.save(item);
	}
}
