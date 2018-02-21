package com.mjamsek.lozigorbox.entities.uporabnik;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "uporabnik")
public class Uporabnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String email;
	
	@Column
	@JsonIgnore
	private String geslo;
	
	@Column(name = "upb_ime")
	private String uporabniskoIme;
	
	//TODO: status
	
	@ManyToMany
	@JoinTable(
			name = "uporabniske_vloge",
			joinColumns = @JoinColumn(name = "upb_id"),
			inverseJoinColumns = @JoinColumn(name = "vloga_id")
	)
	private Set<Vloga> vloge;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGeslo() {
		return geslo;
	}
	
	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}
	
	public String getUporabniskoIme() {
		return uporabniskoIme;
	}
	
	public void setUporabniskoIme(String uporabniskoIme) {
		this.uporabniskoIme = uporabniskoIme;
	}
	
	public Set<Vloga> getVloge() {
		return vloge;
	}
	
	public void setVloge(Set<Vloga> vloge) {
		this.vloge = vloge;
	}
	
	@Override
	public String toString() {
		 StringBuilder sb = new StringBuilder();
		 sb.append("[");
		 if(this.vloge != null) {
		 	boolean prvi = true;
		 	for(Vloga vloga: this.vloge) {
		 		if(prvi) {
		 			sb.append(vloga.getSifra());
		 			prvi = false;
				} else {
		 			sb.append(", " + vloga.getSifra());
				}
			}
		 }
		 sb.append("]");
		 String vloge = sb.toString();
		 return String.format("Uporabnik = { id : %s, uporabniskoIme : %s, geslo : %s, email : %s, vloge : %s }",
				 id, uporabniskoIme, geslo, email, vloge);
	}
	
	public boolean imaVlogo(String sifra) {
		for(Vloga vloga: this.vloge) {
			if(vloga.getSifra().equals(sifra)) {
				return true;
			}
		}
		return false;
	}
	
}
