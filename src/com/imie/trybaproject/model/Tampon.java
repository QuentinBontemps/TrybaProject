package com.imie.trybaproject.model;

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
	
	public String getSerializableString(){
		StringBuilder sb = new StringBuilder();
		String separator = "~";
		
		sb.append(getId());
		sb.append(separator);
		sb.append(getName());
		sb.append(separator);
		sb.append(getQuantity());
		
		return sb.toString();
	}
	
	public void setTamponWithSerializableString(String str) throws Exception{
		String[] tamponStr = str.split("~");
		if(tamponStr.length == 3 ){
			this.setId(Integer.parseInt(tamponStr[0]));
			this.setName(tamponStr[1]);
			this.setQuantity(Integer.parseInt(tamponStr[2]));	
		}else{
			throw new Exception("Il manque des paramètre dans la chaine du " +
					"tampon");
		}
	}
	
}
