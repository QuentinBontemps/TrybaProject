package com.imie.trybaproject.model;

import java.util.ArrayList;

public class ClientOrder {

	private long id;
	private String customer;
	private String typeProduct;
	private int quantity;
	
	public ClientOrder(){
	}
	
	
	public ClientOrder(String customer, String typeProduct, int quantity) {
		super();
		this.customer = customer;
		this.typeProduct = typeProduct;
		this.quantity = quantity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getTypeProduct() {
		return typeProduct;
	}
	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
