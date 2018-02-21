package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.repositories.DatotekaRepository;
import com.mjamsek.lozigorbox.services.DatotekaService;
import com.mjamsek.lozigorbox.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.util.List;

@Service
public class DatotekaServiceImpl implements DatotekaService {
	
	@Inject
	private FileService fileService;
	
	@Inject
	private DatotekaRepository datotekaRepository;
	
	
	@Override
	public void shraniDatoteko(MultipartFile file) {
		Datoteka dat = new Datoteka(file);
		fileService.shraniNaDisk(file);
		Datoteka obstojeca = datotekaRepository.findByLokacija(file.getOriginalFilename());
		if(obstojeca == null) {
			datotekaRepository.save(dat);
		} else {
			obstojeca.posodobi(file);
			datotekaRepository.save(obstojeca);
		}
	}
	
	@Override
	public void shraniDatoteke(MultipartFile[] files) {
		for(MultipartFile file : files) {
			this.shraniDatoteko(file);
		}
	}
	
	@Override
	public void izbrisiDatoteko(Datoteka datoteka) {
		fileService.izbrisiDatoteko(datoteka.getLokacija());
		datotekaRepository.delete(datoteka.getId());
	}
	
	@Override
	public void posodobiDatoteko(Datoteka datoteka) {
		datotekaRepository.save(datoteka);
	}
	
	@Override
	public List<Datoteka> pridobiSeznamDatotek() {
		return datotekaRepository.findAll();
	}
	
	@Override
	public Datoteka pridobiDatoteko(long id) {
		return datotekaRepository.getOne(id);
	}
}
