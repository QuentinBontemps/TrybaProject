package com.imie.trybaproject.model;

public class Station extends Zone{

	private Tampon tampon;
	private boolean visible;
	private int order;
	
	

	public Station(){
		
	}
	
	public Station(String name) {
		super(name);
		this.visible = true;
	}
	
	public Station(String name, int ordre) {
		super(name);
		this.order = ordre;
		this.visible = true;
	}

	public Tampon getTampon() {
		return tampon;
	}
	public void setTampon(Tampon tampon) {
		this.tampon = tampon;
	}
	public boolean isVisible() {
		return visible;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setTamponWithSerializableString(String str) throws Exception{
		this.tampon.setTamponWithSerializableString(str);
	}
	
	public String toString(){
		return this.getName();
		
	}
	
	public String getSerializableString(){
		StringBuilder sb = new StringBuilder();
		String separator = "~";
		
		sb.append(getId());
		sb.append(separator);
		sb.append(getName());
		sb.append(separator);
		if(getTampon() != null)
			sb.append(getTampon().getSerializableString());
		
		return sb.toString();
	}
	
	public void setStationWithSerializableString(String str) throws Exception{
		String[] stationStr = str.split("~");
		if(stationStr.length == 5){
			this.setId(Integer.parseInt(stationStr[0]));
			this.setName(stationStr[1]);
			StringBuilder strTampon = new StringBuilder();
			strTampon.append(stationStr[2]);
			strTampon.append(stationStr[3]);
			strTampon.append(stationStr[4]);
			setTamponWithSerializableString(strTampon.toString());
			
		}else if(stationStr.length == 2){
			this.setId(Integer.parseInt(stationStr[0]));
			this.setName(stationStr[1]);
		}else{
			throw new Exception("Il manque des paramètre dans la chaine de " +
					"la station");
		}
	}
	
	public void setVisible(int i)
	{
		if (i == 1)
		{
			this.visible = true;
		}else
			this.visible = false;
	}
}
