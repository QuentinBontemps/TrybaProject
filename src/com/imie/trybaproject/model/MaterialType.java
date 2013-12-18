package com.imie.trybaproject.model;

import java.util.Iterator;

public enum MaterialType {
	ALUMINIUM ("Aluminium",1),
	BOIS ("Bois",2),
	ACIER ("Acier",3);
	
	  private String name = "";
	  private int value = 0;
	   
	  //Constructor
	  MaterialType(String name, int value){
	    this.name = name;
	    this.value = value;
	  }
	   
	  public String toString(){
	    return name;
	  }
	  
	  public int getValue()
	  {
		  return this.value;
	  }
	  
	  public static MaterialType initMaterialTypeByValue(int i)
	  {
		  MaterialType materialType = null;
		  
		  
		  for(MaterialType mt : MaterialType.values()){
		        if (mt.getValue() == i)
		        	materialType = mt;
		    }
		  
		  return materialType;
	  }
	  
	  public static MaterialType initMaterialTypeByString(String s)
	  {
		  MaterialType materialType = null;
		  
		  
		  for(MaterialType mt : MaterialType.values()){
		        if (mt.toString().equals(s))
		        	materialType = mt;
		    }
		  
		  return materialType;
	  }
}
