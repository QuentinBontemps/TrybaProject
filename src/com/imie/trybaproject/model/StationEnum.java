package com.imie.trybaproject.model;

public enum StationEnum {
	STOCK ("Stock",1),
	DECOUPE("Découpe",2),
	FACONNAGE("Façonnage",3),
	PEINTURE("Peinture",4),
	ASSEMBLAGE("Assemblage",5),
	PRODUCTION("Production",6);
	
	  private String name = "";
	  private int ordre = 0;
	   
	  //Constructeur
	  StationEnum(String name, int ordre){
	    this.name = name;
	    this.ordre = ordre;
	  }
	   
	  public String toString(){
	    return name;
	  }
	  
	  public int getOrdre()
	  {
		  return this.ordre;
	  }
	 
	  
	  public static StationEnum initProductTypeByOrdre(int i)
	  {
		  StationEnum stationEnum = null;
		  
		  
		  for(StationEnum mt : StationEnum.values()){
		        if (mt.getOrdre() == i)
		        	stationEnum = mt;
		    }
		  
		  return stationEnum;
	  }
	  
	  public static StationEnum initProductTypeByString(String s)
	  {
		  StationEnum stationEnum = null;
		  
		  
		  for(StationEnum mt : StationEnum.values()){
			  	if (mt.toString().equals(s))
			  		stationEnum = mt;
		    }
		  
		  return stationEnum;
	  }
}
