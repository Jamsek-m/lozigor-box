package com.mjamsek.lozigorbox.entities.dovoljenja;

import com.mjamsek.lozigorbox.entities.uporabnik.Uporabnik;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skupina")
public class Skupina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String sifra;
	
	@Column
	private String naziv;
	
	@ManyToMany
	@JoinTable(
			name = "clanstvo_skupin",
			joinColumns = @JoinColumn(name = "skupina_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "upb_id", referencedColumnName = "id")
	)
	private Set<Uporabnik> clani;
	
	@OneToMany(mappedBy = "skupina", cascade = CascadeType.ALL)
	private Set<Dovoljenje> dovoljenja;
	
	public Skupina() {
	}
	
	public Skupina(String sifra, String naziv, Set<Uporabnik> clani, Set<Dovoljenje> dovoljenja) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.clani = clani;
		this.dovoljenja = dovoljenja;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
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
	
	public Set<Uporabnik> getClani() {
		return clani;
	}
	
	public void setClani(Set<Uporabnik> clani) {
		this.clani = clani;
	}
	
	public Set<Dovoljenje> getDovoljenja() {
		return dovoljenja;
	}
	
	public void setDovoljenja(Set<Dovoljenje> dovoljenja) {
		this.dovoljenja = dovoljenja;
	}
}
