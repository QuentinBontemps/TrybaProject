package com.imie.trybaproject.model;

public class Order {

	
		
	public Order(){
		
	}	
	
	public Order(String customer, String typeItem, int quantity,
			Product products) {
		super();
		this.customer = customer;
		this.typeItem = typeItem;
		this.quantity = quantity;
		this.products = products;
	}
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getTypeItem() {
		return typeItem;
	}
	public void setTypeItem(String typeItem) {
		this.typeItem = typeItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProducts() {
		return products;
	}
	public void setProducts(Product products) {
		this.products = products;
	}
	
	
}
