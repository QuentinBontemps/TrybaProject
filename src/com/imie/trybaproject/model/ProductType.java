package com.imie.trybaproject.model;

public enum ProductType {
	PORTE ("Porte",1),
	FENETRE ("Fenêtre",2);
	
	  private String name = "";
	  private int value = 0;
	   
	  //Constructeur
	  ProductType(String name, int value){
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
	 
	  
	  public static ProductType initProductTypeByValue(int i)
	  {
		  ProductType materialType = null;
		  
		  
		  for(ProductType mt : ProductType.values()){
		        if (mt.getValue() == i)
		        	materialType = mt;
		    }
		  
		  return materialType;
	  }
	  
	  public static ProductType initProductTypeByString(String s)
	  {
		  ProductType productType = null;
		  
		  
		  for(ProductType mt : ProductType.values()){
			  	if (mt.toString().equals(s))
			  		productType = mt;
		    }
		  
		  return productType;
	  }
	  
	  
}
