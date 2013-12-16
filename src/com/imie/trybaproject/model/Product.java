package com.imie.trybaproject.model;

public class Product {
	
	private long id;
	private String name;
	
	public Product(){
		
	}
	
	public Product(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
