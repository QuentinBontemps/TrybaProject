package com.imie.trybaproject.model;

import java.util.Date;

public class Log {
	
	private long id;
	private User user;
	private Zone zone;
	private Date date;
	
	public Log(){
		
	}
	
	public Log(User user, Zone zone, Date date) {
		super();
		this.user = user;
		this.zone = zone;
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
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
