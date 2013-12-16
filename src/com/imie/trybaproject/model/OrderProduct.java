package com.imie.trybaproject.model;

public class OrderProduct {

	private long orderId;
	private long productId;
	
	public OrderProduct(){
		
	}
	
	public OrderProduct(long orderId, long productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	

	
	
}
