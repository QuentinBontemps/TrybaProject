package com.imie.trybaproject.model;

public class Station extends Zone{

	private Tampon tampon;
	
	public Station(){
		
	}
	
	public Station(String name) {
		super(name);
	}

	public Tampon getTampon() {
		return tampon;
	}

	public void setTampon(Tampon tampon) {
		this.tampon = tampon;
	}
	
	public String toString(){
		return this.getName();
		
	}
	
}
