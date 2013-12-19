package com.imie.trybaproject.model;

import java.io.Serializable;


public class ClientOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8441716286864623409L;
	
	private long id;
	private String customer;
	private String typeProduct;
	private String typeMaterial;
	private int quantity;
	
	public ClientOrder(){
	}
	
	
	public ClientOrder(String customer, String typeProduct, String typeMaterial, int quantity) {
		super();
		this.customer = customer;
		this.typeProduct = typeProduct;
		this.typeMaterial = typeMaterial;
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
	public String getTypeMaterial() {
		return typeMaterial;
	}
	public void setTypeMaterial(String typeMaterial) {
		this.typeMaterial = typeMaterial;
	}
	
}
