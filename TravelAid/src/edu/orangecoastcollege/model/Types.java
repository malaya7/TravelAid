package edu.orangecoastcollege.model;

public enum Types 
{
// Food 
Vegetable, Fruit, dairy_products,  Meat,	
// transportations
Public_transportation, Private_transportation,
// currency
Dollars, Euro,
// measurements
 Lb,Kg,G,L;
	
	 public String toString()
	    {
	        return name().charAt(0) + name().substring(1).toLowerCase().replaceAll("_", " ");
	    }

}
