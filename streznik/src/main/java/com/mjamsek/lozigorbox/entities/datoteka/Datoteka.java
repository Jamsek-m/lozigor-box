package com.mjamsek.lozigorbox.entities.datoteka;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "datoteka")
public class Datoteka implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String ime;
	
	@Column
	private String lokacija;
	
	@Column
	private String ext;
	
	@Column
	private String contentType;
	
	@Column
	private long velikost;
	
	@Column
	private Date uploaded;
	
	public Datoteka() {}
	
	public Datoteka(MultipartFile file) {
		this.posodobi(file);
	}
	
	public void posodobi(MultipartFile file) {
		this.lokacija = file.getOriginalFilename();
		this.uploaded = new Date();
		this.velikost = file.getSize();
		this.contentType = file.getContentType();
		
		String[] imena = file.getOriginalFilename().split("\\.");
		this.ext = imena[imena.length-1].toLowerCase();
		this.ime = extractNameFromFilename(imena);
	}
	
	private String extractNameFromFilename(String[] imena) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < imena.length; i++) {
			if(i != imena.length-1) {
				if(i == 0) {
					sb.append(imena[i]);
				} else {
					sb.append(".");
					sb.append(imena[i]);
				}
			}
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "{\"Datoteka\":{"
				+ "\"id\":\"" + id + "\""
				+ ",\"ime\":\"" + ime + "\""
				+ ",\"lokacija\":\"" + lokacija + "\""
				+ ",\"ext\":\"" + ext + "\""
				+ ",\"contentType\":\"" + contentType + "\""
				+ ",\"velikost\":\"" + velikost + "\""
				+ ",\"uploaded\":" + uploaded
				+ "}}";
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public long getVelikost() {
		return velikost;
	}
	
	public void setVelikost(long velikost) {
		this.velikost = velikost;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getIme() {
		return ime;
	}
	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getLokacija() {
		return lokacija;
	}
	
	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public Date getUploaded() {
		return uploaded;
	}
	
	public void setUploaded(Date uploaded) {
		this.uploaded = uploaded;
	}
}
