package edu.orangecoastcollege.model;

import javafx.fxml.FXML;

public interface convertions 
{

	@FXML
	public boolean addFinalScene();
	
default double fromGallonsToLiters(double Gallons)
{
	return Gallons * 3.78541;
}

default double fromLitersToGallons(double Liters)
{
	return Liters / 3.78541;
}

default double from$toE(double Dollars)
{
	return Dollars / 0.843;
}

default double fromEto$(double E)
{
	return E * 0.843;
}

default double fromLbtoKg(double lb)
{
	return lb / 2.20462;
}

default double fromKgToLb(double kg)
{
	return kg * 2.20462;
}

/**
 * This function will convert from menoy per unit to a diffrent scale
 * supproted currencies $ and E only
 * supported unites: G, L, Lb, Kg Only
 * @param Type intial unit 
 * @param Type intial currency 
 * @para  double amount to be converted
 * @param Type required unit 
 * @param Type required currency
 * @return the converted value up to three decimeal places.
 */
public default double toMoneyPerUnit(Types intialUnit, Types  intialcurrecny, double amount, Types unit, Types currency)
{
	if(intialUnit.equals(Types.Lb) && intialcurrecny.equals(Types.Dollars))
	{
		 amount = from$toE(amount);
		 return fromLbtoKg(amount);
	}
	else if (intialUnit.equals(Types.Kg) && intialcurrecny.equals(Types.Euro))
	{
		 amount = fromEto$(amount);
		 return fromKgToLb(amount);
	}
	return amount;
}
	
}
