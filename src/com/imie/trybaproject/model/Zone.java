package com.imie.trybaproject.model;

import java.util.ArrayList;

public abstract class Zone {

	private long id;
	private String name;
		
	public Zone(){}	
	
	public Zone(String name) {
		super();
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSerializableString(){
		StringBuilder sb = new StringBuilder();
		String separator = "~";
		
		sb.append(getId());
		sb.append(separator);
		sb.append(getName());
		
		return sb.toString();
	}
	
	public ArrayList<Object> getSerializableArray(){
		ArrayList<Object> array = new ArrayList<Object>();
		array.add(String.valueOf(getId()));
		array.add(getName());
		return array;
	}
	
	public void setWithSerializableString(String str) throws Exception{
		String[] userStr = str.split("~");
		if(userStr.length >= 2 ){
			this.setId(Integer.parseInt(userStr[0]));
			this.setName(userStr[1]);
			
		}else{
			throw new Exception("Il manque des paramètre dans la chaine de " +
					"la zone");
		}
	}
	
	public void setWithSerializableArray(ArrayList<Object> str) throws Exception
	{
		if(str.size() >= 2){
			this.setId(Integer.parseInt((String)str.get(0)));
			this.setName((String)str.get(1));
		}else{
			throw new Exception("Il manque des paramètre dans le tableau de " +
					"la zone");
		}
	}
	
}
