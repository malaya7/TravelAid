package edu.orangecoastcollege.model;

public class City extends Country 
{
	String cityName;
	double average_temperature;
	double  minimum_wage;
	
	public City(String name, String climate, int population, String cityName, double average_temperature,
			double minimum_wage) 
	{
		super(name, climate, population);
		this.cityName = cityName;
		this.average_temperature = average_temperature;
		this.minimum_wage = minimum_wage;
	}
	
	@Override
	public String toString()
	{
	    return "City [" + cityName + ", average_temperature=" + average_temperature + ", minimum_wage=" + minimum_wage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(average_temperature);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		temp = Double.doubleToLongBits(minimum_wage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (Double.doubleToLongBits(average_temperature) != Double.doubleToLongBits(other.average_temperature))
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (Double.doubleToLongBits(minimum_wage) != Double.doubleToLongBits(other.minimum_wage))
			return false;
		return true;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getAverage_temperature() {
		return average_temperature;
	}

	public void setAverage_temperature(double average_temperature) {
		this.average_temperature = average_temperature;
	}

	public double getMinimum_wage() {
		return minimum_wage;
	}

	public void setMinimum_wage(double minimum_wage) {
		this.minimum_wage = minimum_wage;
	}
	
}
