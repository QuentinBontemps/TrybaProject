package com.imie.trybaproject.model;

import java.util.ArrayList;

public class ClientOrder {

	private long id;
	private String customer;
	private String typeProduct;
	private int quantity;
	private ArrayList<Product> products;
	
	public ClientOrder(){
		products = new ArrayList<Product>();
	}
	
	
	public ClientOrder(String customer, String typeProduct, int quantity,
			ArrayList<Product> products) {
		super();
		this.customer = customer;
		this.typeProduct = typeProduct;
		this.quantity = quantity;
		this.products = products;
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
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	
}
