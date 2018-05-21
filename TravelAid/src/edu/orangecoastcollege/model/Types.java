package edu.orangecoastcollege.model;

public enum Types
{
// Food
Vegetable, Fruit, Meat,Transportation,RealEstate,Dairy,
// currency
Dollars, Euro,
// measurements
 Lb,Kg,G,L;

	 public String toString()
	    {
	        return name().charAt(0) + name().substring(1).toLowerCase().replaceAll("_", " ");
	    }

}
