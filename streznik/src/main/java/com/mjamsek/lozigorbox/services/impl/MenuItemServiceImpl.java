package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import com.mjamsek.lozigorbox.entities.menu.MenuItemType;
import com.mjamsek.lozigorbox.entities.requests.Direktorij;
import com.mjamsek.lozigorbox.entities.responses.MenuItemResponse;
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
	public MenuItemResponse pridobiPrvoStran() {
		MenuItem korenski = pridobiKorenskiElement();
		MenuItemResponse response = new MenuItemResponse(
				korenski.getId(), korenski.getParent(), korenski.getIme(),
				menuItemRepository.findAllByParent(korenski.getId())
		);
		return response;
	}
	
	@Override
	public MenuItemResponse pridobiOtrokeElementa(long id) {
		MenuItem item = menuItemRepository.findOne(id);
		MenuItemResponse response = new MenuItemResponse(
				item.getId(), item.getParent(), item.getIme(),
				menuItemRepository.findAllByParent(item.getId())
		);
		return response;
	}
	
	@Override
	public List<MenuItem> poisciZQueryjem(String query) {
		return this.menuItemRepository.poisciZQueryjem(query);
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
