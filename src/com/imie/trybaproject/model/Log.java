package com.imie.trybaproject.model;

import java.util.Date;

public class Log {
	
	private long id;
	private User user;
	private String zoneName;
	private Product product;
	private Date date;
	
	public Log(){
	}
	
	public Log(User user, String zoneName, Date date) {
		super();
		this.user = user;
		this.zoneName = zoneName;
		this.date = date;
	}
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
