package com.mjamsek.lozigorbox.entities.dovoljenja;

import javax.persistence.*;

@Entity
@Table(name = "dovoljenje_tip")
public class DovoljenjeTip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String sifra;
	
	@Column
	private String naziv;
	
	public DovoljenjeTip() {
	}
	
	public DovoljenjeTip(String sifra, String naziv) {
		this.sifra = sifra;
		this.naziv = naziv;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSifra() {
		return sifra;
	}
	
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
