package edu.orangecoastcollege.model;

public class Grocery
{
  String description;
  String unit; 
  double price; 
  
  // TODO We need a way to asstioacte this food with a country 
  int country_code;
  
  // using enum for type which could be frit,veg, meat, dairy
  Types type;

public Grocery(String description, String unit, double price, int country_code, Types type) 
{
	this.description = description;
	this.unit = unit;
	this.price = price;
	this.country_code = country_code;
	this.type = type;
}

@Override
public String toString()
{
    return "[" + type.name() + ", Kind=" + description + ", Price=" + price +  unit +"]";
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getUnit() {
	return unit;
}

public void setUnit(String unit) {
	this.unit = unit;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public int getCountry_code() {
	return country_code;
}

public void setCountry_code(int country_code) {
	this.country_code = country_code;
}

public Types getType() {
	return type;
}

public void setType(Types type) {
	this.type = type;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + country_code;
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	long temp;
	temp = Double.doubleToLongBits(price);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
	Grocery other = (Grocery) obj;
	if (country_code != other.country_code)
		return false;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
		return false;
	if (type != other.type)
		return false;
	if (unit == null) {
		if (other.unit != null)
			return false;
	} else if (!unit.equals(other.unit))
		return false;
	return true;
}

}
