package com.imie.trybaproject.model;

import android.app.Application;

import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;

public class Product {
	
	private long id;
	private String name;
	private ClientOrder order;
	private Zone currentZone;
	private ZoneType currentTypeZone;
	
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

	public ZoneType getCurrentTypeZone() {
		return currentTypeZone;
	}

	public void setCurrentTypeZone(ZoneType currentTypeZone) {
		this.currentTypeZone = currentTypeZone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientOrder getOrder() {
		return order;
	}

	public void setOrder(ClientOrder order) {
		this.order = order;
	}

	public Zone getCurrentZone() {
		return currentZone;
	}

	public void setCurrentZone(Zone currentZone) {
		this.currentZone = currentZone;
	}
	
	
	public int goToNextStation(Station station)
	{
		int result = 0;
		Station myStation;
		int nextOrder = 0;
		
		// On va vérifier que la station passé en paramètre est bien la prochaine dans l'ordre
		if (this.currentTypeZone == ZoneType.STATION)
		{
			
			myStation = (Station) this.currentZone;
			nextOrder = myStation.getOrder() + 1;
			if (nextOrder == station.getOrder())
			{
				// On peut passer la station dans la prochaine station
				this.setCurrentZone(station);
				return 1;
			}else{
				return 2;
			}
			
			
		}
		
		return result;
		
	}
	
}

