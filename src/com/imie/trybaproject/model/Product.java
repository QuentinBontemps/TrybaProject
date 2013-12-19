package com.imie.trybaproject.model;

import java.util.Date;

import android.app.Application;

import com.imie.trybaproject.db.ApplicationSQLiteOpenHelper;
import com.imie.trybaproject.db.LogAdapter;
import com.imie.trybaproject.db.ProductAdapter;
import com.imie.trybaproject.db.StationAdapter;

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
	
	
	public int goToNextTampon(Station station, ApplicationSQLiteOpenHelper helper, User user)
	{
		int result = 0;
		Station myStation;
		Station nextStation;
		StationAdapter stationAdapter = new StationAdapter(helper);
		ProductAdapter productAdapter = new ProductAdapter(helper);
		LogAdapter logAdapter = new LogAdapter(helper);
		
		if (this.currentTypeZone == ZoneType.STATION)
		{
			
			myStation = (Station) this.currentZone;
			

			int nextOrder = myStation.getOrder() + 1;
			nextStation = stationAdapter.getByOrder(nextOrder);
			
			if (myStation.getOrder() == station.getOrder())
			{
				Tampon nextTampon = nextStation.getTampon();
				
				// On peut passer la station dans le prochain tampon
				this.setCurrentZone(nextTampon);
				// il reste a mettre en BDD les modifications
				productAdapter.update(this);
				
				Log log = new Log();
				log.setProduct(this);
				log.setUser(user);
				log.setZone(nextTampon);
				log.setDate(new Date());
				
				logAdapter.insert(log);
				return 1;
			}else{
				return 2;
			}
			
			
		}else
		{
			result = 0;
		}
		
		return result;
		
	}
	
	public int goToNextStation(Station station, ApplicationSQLiteOpenHelper helper, User user)
	{
		int result = 0;
		Station myStation;
		Station nextStation;
		StationAdapter stationAdapter = new StationAdapter(helper);
		ProductAdapter productAdapter = new ProductAdapter(helper);
		LogAdapter logAdapter = new LogAdapter(helper);
		
		if (this.currentTypeZone == ZoneType.TAMPON)
		{
			
			myStation = stationAdapter.getByTampon((Tampon) this.getCurrentZone());
			

			//int nextOrder = myStation.getOrder() + 1;
			nextStation = station;
			
			if (myStation.getOrder() == station.getOrder())
			{
				
				// On peut passer la station dans le prochain tampon
				this.setCurrentZone(nextStation);
				// il reste a mettre en BDD les modifications
				productAdapter.update(this);
				
				Log log = new Log();
				log.setProduct(this);
				log.setUser(user);
				log.setZone(nextStation);
				log.setDate(new Date());
				
				logAdapter.insert(log);
				return 1;
			}else{
				return 2;
			}
			
			
		}else
		{
			result = 0;
		}
		
		return result;
		
	}
}

