package com.imie.trybaproject.model;

import java.util.ArrayList;

public class Station extends Zone{

	private Tampon tampon;
	
	public Station(){
		
	}
	
	public Station(String name) {
		super(name);
	}

	public Tampon getTampon() {
		return tampon;
	}

	public void setTampon(Tampon tampon) {
		this.tampon = tampon;
	}
	
	public void setTamponWithSerializableString(String str) throws Exception{
		this.tampon.setWithSerializableString(str);
	}
	
	public void setTamponWithSerializableArray(ArrayList<Object> array) throws Exception{
		this.tampon.setWithSerializableArray(array);
	}
	
	public String toString(){
		return this.getName();
		
	}
	
	public String getSerializableString(){
		StringBuilder sb = new StringBuilder();
		String separator = "~";
		
		sb.append(super.getSerializableString());
		
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
		if(stationStr.length == 5){
			super.setWithSerializableString(str);
			StringBuilder strTampon = new StringBuilder();
			strTampon.append(stationStr[2]);
			strTampon.append(stationStr[3]);
			strTampon.append(stationStr[4]);
			setTamponWithSerializableString(strTampon.toString());
			
		}else if(stationStr.length == 2){
			super.setWithSerializableString(str);
		}else{
			throw new Exception("Il manque des paramètre dans la chaine de " +
					"la station");
		}
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
