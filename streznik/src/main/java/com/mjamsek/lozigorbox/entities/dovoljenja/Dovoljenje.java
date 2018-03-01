package com.mjamsek.lozigorbox.entities.dovoljenja;

import com.mjamsek.lozigorbox.entities.menu.MenuItem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dovoljenje")
public class Dovoljenje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "tip")
	private DovoljenjeTip tip;
	
	@Column
	@ManyToMany(mappedBy = "dovoljenja")
	private Set<MenuItem> items;
	
	@ManyToOne
	@JoinColumn(name = "skupina_id")
	private Skupina skupina;
	
	public Dovoljenje() {
	}
	
	public Dovoljenje(DovoljenjeTip tip, Skupina skupina) {
		this.tip = tip;
		this.skupina = skupina;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public DovoljenjeTip getTip() {
		return tip;
	}
	
	public void setTip(DovoljenjeTip tip) {
		this.tip = tip;
	}
	
	public Set<MenuItem> getItems() {
		return items;
	}
	
	public void setItems(Set<MenuItem> items) {
		this.items = items;
	}
	
	public Skupina getSkupina() {
		return skupina;
	}
	
	public void setSkupina(Skupina skupina) {
		this.skupina = skupina;
	}
}
