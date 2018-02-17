package com.mjamsek.lozigorbox.repositories;

import com.mjamsek.lozigorbox.entities.uporabnik.Vloga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VlogaRepository extends JpaRepository<Vloga, Integer> {
	
	public Vloga findBySifra(String sifra);


}
