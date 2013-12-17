package com.imie.trybaproject.model;

public class Tampon extends Zone{

	private int quantity;

	public Tampon() {}
	
	public Tampon(String name, int quantity) {
		super(name);
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
