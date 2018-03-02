package com.mjamsek.lozigorbox.repositories;

import com.mjamsek.lozigorbox.entities.dovoljenja.DovoljenjeTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipDovoljenjaRepository extends JpaRepository<DovoljenjeTip, Integer> {
	
	public DovoljenjeTip findBySifra(String sifra);
	
	public List<DovoljenjeTip> findAll();
	
}
