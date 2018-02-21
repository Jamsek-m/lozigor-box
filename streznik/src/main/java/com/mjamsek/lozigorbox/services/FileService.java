package com.mjamsek.lozigorbox.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public interface FileService {
	
	public final Path UPLOAD_DIRECTORY = Paths.get("storage");
	
	public void initStorage();
	
	public void shraniNaDisk(MultipartFile file);
	
	public Stream<Path> pridobiSeznamVsehDatotekNaDisku();
	
	public Path pridobiDatoteko(String filename);
	
	public void izbrisiDatoteko(String filename);
	
	public Resource pridobiKotResurs(String filename);
	
	public void izprazniHrambo();
	
}
