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
	  
	  public void initUserTypeByValue(int i)
	  {
		  switch (i) {
			case 1:
				this.value = 1;
				this.name = "operator";
				break;
			case 2:
				this.value = 2;
				this.name = "administrator";
				break;
			default:
				break;
			}
	  }
}
