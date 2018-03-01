package com.mjamsek.lozigorbox.entities.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.dovoljenja.Dovoljenje;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "menu_item")
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private long parent;
	
	@Column
	private String ime;
	
	@Formula("(SELECT COUNT(*) FROM menu_item m WHERE m.parent = id)")
	private long num_of_childs;
	
	@Enumerated(EnumType.STRING)
	@Column
	private MenuItemType tip;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private Datoteka file;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "item_dovoljenja",
			joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "dovoljenje_id", referencedColumnName = "id")
	)
	private Set<Dovoljenje> dovoljenja;
	
	public MenuItem() {
	}
	
	public String getIme() {
		return ime;
	}
	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getParent() {
		return parent;
	}
	
	public void setParent(long parent) {
		this.parent = parent;
	}
	
	public long getNum_of_childs() {
		return num_of_childs;
	}
	
	public void setNum_of_childs(long num_of_childs) {
		this.num_of_childs = num_of_childs;
	}
	
	public MenuItemType getTip() {
		return tip;
	}
	
	public void setTip(MenuItemType tip) {
		this.tip = tip;
	}
	
	public Datoteka getFile() {
		return file;
	}
	
	public void setFile(Datoteka file) {
		this.file = file;
	}
	
	public Set<Dovoljenje> getDovoljenja() {
		return dovoljenja;
	}
	
	public void setDovoljenja(Set<Dovoljenje> dovoljenja) {
		this.dovoljenja = dovoljenja;
	}
}
