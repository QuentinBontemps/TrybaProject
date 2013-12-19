package com.imie.trybaproject.model;

public enum ZoneType {
	STATION ("Station",1),
	TAMPON ("Tampon",2);
	
	  private String name = "";
	  private int value = 0;
	   
	  //Constructeur
	  ZoneType(String name, int value){
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
	 
	  
	  public static ZoneType initProductTypeByValue(int i)
	  {
		  ZoneType zoneType = null;
		  
		  
		  for(ZoneType mt : ZoneType.values()){
		        if (mt.getValue() == i)
		        	zoneType = mt;
		    }
		  
		  return zoneType;
	  }
	  
	  public static ZoneType initProductTypeByString(String s)
	  {
		  ZoneType zoneType = null;
		  
		  
		  for(ZoneType mt : ZoneType.values()){
			  	if (mt.toString().equals(s))
			  		zoneType = mt;
		    }
		  
		  return zoneType;
	  }
}
