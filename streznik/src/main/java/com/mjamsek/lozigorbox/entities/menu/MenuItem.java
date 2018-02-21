package com.mjamsek.lozigorbox.entities.menu;

import com.mjamsek.lozigorbox.entities.datoteka.Datoteka;

import javax.persistence.*;

@Entity
@Table(name = "menu_item")
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private long parent;
	
	@Column
	private long num_of_childs;
	
	@Enumerated(EnumType.STRING)
	@Column
	private MenuItemType tip;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private Datoteka file;
	
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
