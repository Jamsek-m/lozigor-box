package com.mjamsek.lozigorbox.services.impl;

import com.mjamsek.lozigorbox.services.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {
	
	@Override
	public void initStorage() {
		try {
			if(!Files.exists(UPLOAD_DIRECTORY)) {
				Files.createDirectory(UPLOAD_DIRECTORY);
			}
		} catch (IOException e) {
			throw new RuntimeException("Ne morem inicializirati upload direktorija!");
		}
	}
	
	@Override
	public void shraniNaDisk(MultipartFile file) {
		try {
			InputStream src = file.getInputStream();
			Path dest = UPLOAD_DIRECTORY.resolve(file.getOriginalFilename());
			Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Napaka pri shranjevanju datoteke!");
		}
	}
	
	@Override
	public Stream<Path> pridobiSeznamVsehDatotekNaDisku() {
		try {
			return Files.walk(UPLOAD_DIRECTORY, 1)
					.filter(path -> !path.equals(UPLOAD_DIRECTORY))
					.map(path -> UPLOAD_DIRECTORY.relativize(path));
		} catch(IOException e) {
			throw new RuntimeException("Napaka pri branju datotek!");
		}
	}
	
	@Override
	public Path pridobiDatoteko(String filename) {
		return UPLOAD_DIRECTORY.resolve(filename);
	}
	
	@Override
	public void izbrisiDatoteko(String filename) {
		try {
			Files.delete(UPLOAD_DIRECTORY.resolve(filename));
		} catch (IOException e) {
			throw new RuntimeException("Napaka pri brisanju datoteke!");
		}
	}
	
	@Override
	public Resource pridobiKotResurs(String filename) {
		try {
			Path file = UPLOAD_DIRECTORY.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Napaka pri nalaganju resursa - datoteka je poskodovana ali ne obstaja");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Napaka pri nalaganju resursa!");
		}
	}
	
	@Override
	public void izprazniHrambo() {
		FileSystemUtils.deleteRecursively(UPLOAD_DIRECTORY.toFile());
	}
}
