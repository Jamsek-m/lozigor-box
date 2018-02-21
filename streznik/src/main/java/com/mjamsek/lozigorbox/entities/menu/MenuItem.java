package com.mjamsek.lozigorbox.entities.menu;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;
import com.mjamsek.lozigorbox.entities.dovoljenja.Skupina;
import org.springframework.data.jpa.repository.Query;

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
	
	@Column
	private long num_of_childs;
	
	@Enumerated(EnumType.STRING)
	@Column
	private MenuItemType tip;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private Datoteka file;
	
	@ManyToMany
	@JoinTable(
			name = "item_skupine",
			joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "skupina_id", referencedColumnName = "id")
	)
	private Set<Skupina> skupine;
	
	public MenuItem() {
	}
	
	public Set<Skupina> getSkupine() {
		return skupine;
	}
	
	public void setSkupine(Set<Skupina> skupine) {
		this.skupine = skupine;
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
}
