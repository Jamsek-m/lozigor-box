package com.mjamsek.lozigorbox.entities.dovoljenja;

import javax.persistence.*;

@Entity
@Table(name = "dovoljenje")
public class Dovoljenje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "tip")
	private DovoljenjeTip tip;
	
	public Dovoljenje() {
	}
	
	public Dovoljenje(DovoljenjeTip tip) {
		this.tip = tip;
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
	
}
