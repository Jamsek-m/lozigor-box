package com.mjamsek.lozigorbox.repositories;

import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UporabnikRepository extends JpaRepository<Uporabnik, Long> {
	
	public Uporabnik findByEmail(String email);
	
	
	
}
