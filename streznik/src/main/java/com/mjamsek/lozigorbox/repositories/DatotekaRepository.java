package com.mjamsek.lozigorbox.repositories;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatotekaRepository extends JpaRepository<Datoteka, Long> {
	
	@Override
	List<Datoteka> findAll();
	
	@Override
	Datoteka getOne(Long aLong);
	
	Datoteka findByLokacija(String lokacija);
}
