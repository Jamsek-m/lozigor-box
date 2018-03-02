package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import com.mjamsek.lozigorbox.entities.menu.MenuItemType;
import com.mjamsek.lozigorbox.entities.requests.Direktorij;
import com.mjamsek.lozigorbox.entities.responses.MenuItemResponse;
import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import com.mjamsek.lozigorbox.repositories.MenuItemRepository;
import com.mjamsek.lozigorbox.repositories.TipDovoljenjaRepository;
import com.mjamsek.lozigorbox.services.MenuItemService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {
	
	@Inject
	private MenuItemRepository menuItemRepository;
	
	@Inject
	private TipDovoljenjaRepository tipDovoljenjaRepository;
	
	@Override
	public MenuItem pridobiKorenskiElement() {
		return menuItemRepository.findByIme(ROOT_ID);
	}
	
	@Override
	public MenuItemResponse pridobiPrvoStran(Uporabnik uporabnik) {
		MenuItem korenski = pridobiKorenskiElement();
		int dovoljenjeBeri = tipDovoljenjaRepository.findBySifra("DOWNLOAD").getId();
		List<MenuItem> items = menuItemRepository
				.poisciVseZParentom(korenski.getId(), uporabnik.getId(), dovoljenjeBeri);
		MenuItemResponse response = new MenuItemResponse(
				korenski.getId(), korenski.getParent(), korenski.getIme(), items
		);
		return response;
	}
	
	@Override
	public MenuItemResponse pridobiOtrokeElementa(long id, Uporabnik uporabnik) {
		MenuItem item = menuItemRepository.findOne(id);
		int dovoljenjeBeri = tipDovoljenjaRepository.findBySifra("DOWNLOAD").getId();
		List<MenuItem> items = menuItemRepository
				.poisciVseZParentom(item.getId(), uporabnik.getId(), dovoljenjeBeri);
		MenuItemResponse response = new MenuItemResponse(
				item.getId(), item.getParent(), item.getIme(), items
		);
		return response;
	}
	
	@Override
	public List<MenuItem> poisciZQueryjem(String query, Uporabnik uporabnik) {
		int dovoljenjeBeri = tipDovoljenjaRepository.findBySifra("DOWNLOAD").getId();
		return this.menuItemRepository.posciZQueryjem(query, uporabnik.getId(),
				dovoljenjeBeri, new PageRequest(0, 20));
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
