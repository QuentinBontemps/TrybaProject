package com.imie.trybaproject.model;

public enum UserType {
	OPERATOR ("operator",1),
	ADMINISTRATOR ("administrator",2);
	
	  private String name = "";
	  private int value = 0;
	   
	  //Constructeur
	  UserType(String name, int value){
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
	  
	 
	  
	  public static UserType initUserTypeByValue(int i)
	  {
		  UserType userType = null;
		  
		  
		  for(UserType mt : UserType.values()){
		        if (mt.getValue() == i)
		        	userType = mt;
		    }
		  
		  return userType;
	  }
	  
	  public static UserType initUserTypeByString(String s)
	  {
		  UserType userType = null;
		  
		  
		  for(UserType mt : UserType.values()){
			  	if (mt.toString().equals(s))
			  		userType = mt;
		    }
		  
		  return userType;
	  }
}
