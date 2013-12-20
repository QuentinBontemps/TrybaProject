package com.imie.trybaproject.model;

import java.util.ArrayList;

public class Tampon extends Zone{

	private int quantity;

	public Tampon() {}
	
	public Tampon(String name, int quantity) {
		super(name);
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getSerializableStringLength(){
		return super.getSerializableStringLength() + 1;
	}
	
	public String getSerializableString(){
		StringBuilder sb = new StringBuilder();
		String separator = "~";
		
		sb.append(super.getSerializableString());
		sb.append(separator);
		sb.append(getQuantity());
		
		return sb.toString();
	}
	
	public ArrayList<Object> getSerializableArray(){
		ArrayList<Object> array = super.getSerializableArray();
		
		array.add(String.valueOf(quantity));
		
		return array;
	}
	
	public void setWithSerializableString(String str) throws Exception{
		String[] tamponStr = str.split("~");
		if(tamponStr.length == this.getSerializableStringLength()){			
			super.setWithSerializableString(str);
			this.setQuantity(Integer.parseInt(tamponStr[2]));	
		}else{
			throw new Exception("Il manque des paramètre dans la chaine du " +
					"tampon");
		}
	}
	
	public void setWithSerializableArray(ArrayList<Object> str) throws Exception
	{
		if(str.size() == this.getSerializableStringLength()){
			super.setWithSerializableArray(str);
			this.quantity = Integer.parseInt((String)str.get(2));
			
		}else{
			throw new Exception("Il manque des paramètre dans le tableau de " +
					"la zone");
		}
	}
	
	
	
}
