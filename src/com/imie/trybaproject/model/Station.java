package com.imie.trybaproject.model;

import java.util.ArrayList;

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
		this.tampon.setWithSerializableString(str);
	}
	
	public void setTamponWithSerializableArray(ArrayList<Object> array) 
															throws Exception{
		this.tampon.setWithSerializableArray(array);
	}
	
	public int getSerializableStringLength(){
		int length = super.getSerializableStringLength();
		length = length + 2;
		
		return length;
	}
	
	public int getSerializableStringLengthWithTampon(){
		if(getTampon() == null){
			this.tampon = new Tampon();
		}
		return this.getSerializableStringLength() + 
				getTampon().getSerializableStringLength();
	}
	
	public String toString(){
		return this.getName();
		
	}
	
	public String getSerializableString(){
		StringBuilder sb = new StringBuilder();
		String separator = "~";
		
		sb.append(super.getSerializableString());
		sb.append(separator);
		sb.append(getOrder());
		sb.append(separator);
		sb.append(isVisible());
		if(getTampon() != null){
			sb.append(separator);
			sb.append(getTampon().getSerializableString());
		}
		
		return sb.toString();
	}
	
	public ArrayList<Object> getSerializableArray(){
		ArrayList<Object> array = super.getSerializableArray();
			
		array.add(getTampon().getSerializableArray());
		
		return array;
	}
	
	public void setWithSerializableString(String str) throws Exception{
		String[] stationStr = str.split("~");
		
		if(stationStr.length >= this.getSerializableStringLength()){
			if(stationStr.length >= this.getSerializableStringLength()){
				super.setWithSerializableString(str);
				setOrder(Integer.parseInt(stationStr[2]));
				setVisible(Boolean.parseBoolean(stationStr[3]));
			}
			
			if(stationStr.length >= this.getSerializableStringLengthWithTampon()){
				StringBuilder strTampon = new StringBuilder();
				String separator = "~";
				strTampon.append(stationStr[4]);
				strTampon.append(separator);
				strTampon.append(stationStr[5]);
				strTampon.append(separator);
				strTampon.append(stationStr[6]);
				setTamponWithSerializableString(strTampon.toString());
			}
			
			
			
	
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
	public void setWithSerializableArray(ArrayList<Object> str) throws Exception
	{
		if(str.size() == 3){
			this.setId(Integer.parseInt((String)str.get(0)));
			this.setName((String)str.get(1));
			this.setTamponWithSerializableArray((ArrayList<Object>)str.get(2));
		}else{
			throw new Exception("Il manque des paramètre dans le tableau de " +
					"la zone");
		}
	}
	
	
	
}
