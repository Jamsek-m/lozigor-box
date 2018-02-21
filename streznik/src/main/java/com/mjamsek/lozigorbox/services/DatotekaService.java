package com.mjamsek.lozigorbox.services;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DatotekaService {
	
	public void shraniDatoteko(MultipartFile file);
	
	public void shraniDatoteke(MultipartFile[] files);
	
	public void izbrisiDatoteko(Datoteka datoteka);
	
	public void posodobiDatoteko(Datoteka datoteka);
	
	public List<Datoteka> pridobiSeznamDatotek();
	
	public Datoteka pridobiDatoteko(long id);
	
}
