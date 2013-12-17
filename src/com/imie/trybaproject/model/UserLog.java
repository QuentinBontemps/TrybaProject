package com.imie.trybaproject.model;

import java.util.Date;

public class UserLog {

	private long id;
	private User user;	
	private Station station;
	private Date dateEntree;
	private Date dateSortie;
	
	public UserLog(){
		
	}
	

	public UserLog(User user, Station station, Date dateEntree,
			Date dateSortie) {
		super();
		this.user = user;
		this.station = station;
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
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

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}
}
