package edu.orangecoastcollege.model;

public class Transportation 
{
	// public or private
	Types type;
	// Train, Bus, Taxi
	private String Kind;
	private int country_code;
	private double averagePrice;
	
	// for private only 
	private double average_economic_car_price;
	/** should have a defult value Gallons*/
	private	double average_gas_price;
	private double average_inssurance_price;
	
	public Transportation(Types type, String kind, int country_code, double averagePrice,
			double average_economic_car_price, double average_gas_price, double average_inssurance_price) 
	{
		super();
		this.type = type;
		Kind = kind;
		this.country_code = country_code;
		this.averagePrice = averagePrice;
		if(this.type.equals(Types.Private_transportation))
		{
			this.average_economic_car_price = average_economic_car_price;
			this.average_gas_price = average_gas_price;
			this.average_inssurance_price = average_inssurance_price;
		}
		else
		{
			this.average_economic_car_price = 0;
			this.average_gas_price = 0;
			this.average_inssurance_price = 0;
		}
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public String getKind() {
		return Kind;
	}

	public void setKind(String kind) {
		Kind = kind;
	}

	public int getCountry_code() {
		return country_code;
	}

	public void setCountry_code(int country_code) {
		this.country_code = country_code;
	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public double getAverage_economic_car_price() {
		return average_economic_car_price;
	}

	public void setAverage_economic_car_price(double average_economic_car_price) {
		this.average_economic_car_price = average_economic_car_price;
	}

	public double getAverage_gas_price() {
		return average_gas_price;
	}

	public void setAverage_gas_price(double average_gas_price) {
		this.average_gas_price = average_gas_price;
	}

	public double getAverage_inssurance_price() {
		return average_inssurance_price;
	}

	public void setAverage_inssurance_price(double average_inssurance_price) {
		this.average_inssurance_price = average_inssurance_price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Kind == null) ? 0 : Kind.hashCode());
		long temp;
		temp = Double.doubleToLongBits(averagePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(average_economic_car_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(average_gas_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(average_inssurance_price);
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
		Transportation other = (Transportation) obj;
		if (Kind == null) {
			if (other.Kind != null)
				return false;
		} else if (!Kind.equals(other.Kind))
			return false;
		if (Double.doubleToLongBits(averagePrice) != Double.doubleToLongBits(other.averagePrice))
			return false;
		if (Double.doubleToLongBits(average_economic_car_price) != Double
				.doubleToLongBits(other.average_economic_car_price))
			return false;
		if (Double.doubleToLongBits(average_gas_price) != Double.doubleToLongBits(other.average_gas_price))
			return false;
		if (Double.doubleToLongBits(average_inssurance_price) != Double
				.doubleToLongBits(other.average_inssurance_price))
			return false;
		if (country_code != other.country_code)
			return false;
		if (type != other.type)
			return false;
		return true;
	}


}
