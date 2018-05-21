package edu.orangecoastcollege.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Country
{
	private String Name;
	private String climate;
	private int population;
	public Country(){}
	

	  protected  NumberFormat YEN = NumberFormat.getCurrencyInstance(Locale.JAPAN);
	  protected NumberFormat DOLAR_REAL = NumberFormat.getCurrencyInstance(Locale.US);
	  protected NumberFormat POUND = NumberFormat.getCurrencyInstance(Locale.UK);
	  protected NumberFormat EURO = NumberFormat.getCurrencyInstance(Locale.ITALY);
	  protected final static String VIETNAM_CURRENCY = "₫";
	 /**
	  * 
	  * @param countryCode Takes in a country code
	  * @return returns the apropiate currency Format for the country
	  */
	  public NumberFormat getCurrencyFormat(int countryCode)
	  {
		  if(countryCode==1)
				return DOLAR_REAL;
			
			if(countryCode==2)
				return POUND;
			
			if(countryCode==3)
				return EURO;
		
			if(countryCode==5)
				return YEN;
			
			if(countryCode==6)
				return DOLAR_REAL;
			//in the case its vietnam return null since Vietnam does not have a currency format
			return null;
			
	  }
	public Country(String name, String climate, int population)
	{
		Name = name;
		this.climate = climate;
		this.population = population;
	}

	@Override
	public String toString()
	{
	    return "Country [" + Name + ", climate=" + climate + ", population=" + population + "Millions]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result + population;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (climate == null) {
			if (other.climate != null)
				return false;
		} else if (!climate.equals(other.climate))
			return false;
		if (population != other.population)
			return false;
		return true;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}	
}
