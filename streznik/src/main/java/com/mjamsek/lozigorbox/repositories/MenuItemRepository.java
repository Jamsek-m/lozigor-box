package com.mjamsek.lozigorbox.repositories;

import com.mjamsek.lozigorbox.entities.menu.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

	public List<MenuItem> findAllByParent(long id);
	
	public MenuItem findByIme(String ime);
	
}
