package edu.orangecoastcollege.model;

import java.text.NumberFormat;
import java.util.Locale;

import edu.orangecoastcollege.controller.Controller;

public class Housing extends Country{

	 String type;
	 double average_rent_price;
	 double average_buying_price;
	 int country_code;
	 //Real-Estate	price in Dong/buy	Average to rent
	 public Housing (String type, double price, double rent)
	 {
		 this.type=type;
		 this.average_buying_price=price;
		 this.average_rent_price=rent;
	 }
	public Housing(String type, double average_rent_price, double average_buying_price, int country_code) {
		this.type = type;
		this.average_rent_price = average_rent_price;
		this.average_buying_price = average_buying_price;
		this.country_code = country_code;
	}

	@Override
	public String toString()
	{	StringBuilder h = new StringBuilder("[");
		if(getCurrencyFormat(this.country_code)==null)
	{	
return			h.append(Controller.getCountryName(this.country_code)).append(", Type:").append(this.type).append(", Average buying price:")
		.append(this.average_buying_price).append(VIETNAM_CURRENCY)
		.append(", Average rent price:").append(this.average_rent_price).append(VIETNAM_CURRENCY).append("]")
		.toString();
	}
		return h.append(Controller.getCountryName(this.country_code))
		.append(", Type:").append(this.type).append(", Average buying price:")
		.append(getCurrencyFormat(this.country_code).format(this.getAverage_buying_price()))
		.append(", Average rent price:").append(getCurrencyFormat(this.country_code).format(this.average_rent_price))
		.append("]").toString();

//	    return "[" + type + ", average buying  price= "+ currency.format(average_rent_price) +" average rent  price=" + currency.format(average_buying_price) +"]";
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAverage_rent_price() {
		return average_rent_price;
	}

	public void setAverage_rent_price(double average_rent_price) {
		this.average_rent_price = average_rent_price;
	}

	public double getAverage_buying_price() {
		return average_buying_price;
	}

	public void setAverage_buying_price(double average_buying_price) {
		this.average_buying_price = average_buying_price;
	}

	public int getCountry_code() {
		return country_code;
	}

	public void setCountry_code(int country_code) {
		this.country_code = country_code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(average_buying_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(average_rent_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + country_code;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Housing other = (Housing) obj;
		if (Double.doubleToLongBits(average_buying_price) != Double.doubleToLongBits(other.average_buying_price))
			return false;
		if (Double.doubleToLongBits(average_rent_price) != Double.doubleToLongBits(other.average_rent_price))
			return false;
		if (country_code != other.country_code)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
