package edu.orangecoastcollege.model;

public class Transportation {

	// Train, Bus, Taxi

	private int country_code;
	private double averagePrice;
	private double averageMonthlyPrice;
	// for private only
	private double average_economic_car_price;
	/** should have a defult value Gallons */
	private double average_gas_price;
	private double average_diesel_price;
	private double average_inssurance_price;
	private Types type;


	//avgEconomicCarPrice, gas,diesel,insurrance,unit,avgMonthlyPass,cCode
	public Transportation(double average_economic_car_price,
			double average_gas_price,double avgDiesel, double average_inssurance_price,Types unit,double avgMonthlyPass, int country_code) {
		super();

		this.average_diesel_price=avgDiesel;
		this.country_code = country_code;
		this.average_economic_car_price = average_economic_car_price;
		this.average_gas_price = average_gas_price;
		this.average_inssurance_price = average_inssurance_price;
		this.type=unit;
		this.averageMonthlyPrice=avgMonthlyPass;
	}



	public double getAverageMonthlyPrice() {
		return averageMonthlyPrice;
	}



	public void setAverageMonthlyPrice(double averageMonthlyPrice) {
		this.averageMonthlyPrice = averageMonthlyPrice;
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


	public double getAveragePublicPrice() {
		return averageMonthlyPrice;
	}


	public void setAveragePublicPrice(double averagePublicPrice) {
		this.averageMonthlyPrice = averagePublicPrice;
	}


	public double getAverage_diesel_price() {
		return average_diesel_price;
	}


	public void setAverage_diesel_price(double average_diesel_price) {
		this.average_diesel_price = average_diesel_price;
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
		long temp;
		temp = Double.doubleToLongBits(averageMonthlyPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(averagePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(average_diesel_price);
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
		if (Double.doubleToLongBits(averageMonthlyPrice) != Double.doubleToLongBits(other.averageMonthlyPrice))
			return false;
		if (Double.doubleToLongBits(averagePrice) != Double.doubleToLongBits(other.averagePrice))
			return false;
		if (Double.doubleToLongBits(average_diesel_price) != Double.doubleToLongBits(other.average_diesel_price))
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
