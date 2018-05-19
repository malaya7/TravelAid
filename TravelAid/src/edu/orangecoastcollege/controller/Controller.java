package edu.orangecoastcollege.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.orangecoastcollege.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Controller {

	private static Controller controller;

	// Our SQLite Database name
	private static final String DB_NAME = "TravelAid.db";
	// Files
	private static final String USA_DAIRY_FILE_DATA_FILE = "USA/Dairy USA.csv";
	private static final String USA_FRUIT_DATA_FILE = "USA/Fruit USA.csv";
	private static final String USA_VEGETABLE_DATA_FILE = "USA/Vegetables USA.csv";
	private static final String USA_MEAT_DATA_FILE = "USA/Meat USA.csv";
	private static final String USA_PRIVATE_TRANSPORTATION_FILE = "USA/USA Transportation.csv";
	private static final String USA_HOUSING_FILE = "USA/USA Housing Price.csv";
	private static final String EU_ANMAL_DATA_FILE = "animal products.csv";
	private static final String EU_DAIRY_DATA_FILE = "dairy products.csv";
	private static final String EU_FRUIT_DAT_FILE = "fruit products.csv";
	private static final String EU_VEGETABLE_DATA_FILE = "vegetable products.csv";
	private static final String SPAIN_HOUSING_FILE = "Housing Spain.csv";
	private static final String Japan_FILE_DATA_FILE = "Japan/Japan.csv";
	private static final String SPAIN_TRANSPORTATION = "Private Transportation Spain.csv";
	private static final String UK_PRIV_TRANSPORTATION = "Private Transportation UK.csv";
	private static final String AllCountries = "CountriesSix.csv";

	// country codes
	public static String USA_COUNTRY_CODE = "1";
	public static String UK_COUNTRY_CODE = "2";
	public static String SPAIN_COUNTRY_CODE = "3";
	public static String VIETNAM_COUNTRY_CODE = "4";
	public static String JAPAN_COUNTRY_CODE = "5";
	public static String BRAZIL_COUNTRY_CODE = "6";
	public static int countryChoosen = 0;
	
	private static final String COUNTRY_TABLE_NAME = "country";
	private static final String[] COUNTRY_TABLE_FIELD_NAME = 
		{ "_id", "country", "population", "climate"};
	private static final String[] COUNTRY_TABLE_FIELD_TYPES =
		{ "INTEGER PRIMARY KEY", "TEXT", "INTEGER", "TEXT"};


    // food _​id PRIMARY KEY​ INTEGER, ​type[enum type] ​TEXT, ​description ​TEXT, ​unit
    // ​TEXT, price ​REAL, ​country_code ​INTEGER
	private static final String FOOD_TABLE_NAME = "food";
	private static final String[] FOOD_TABLE_FIELD_NAME = { "_id", "type", "description", "unit", "price", "country_code" };
	private static final String[] FOOD_TABLE_FIELD_TYPE = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "REAL",
    "INTEGER" };
	// private ​_id PRIMARY KEY INTEGER​, ​average_economic_car_price ​REAL
    // ,​average_gas_price​ REAL, ​averge_diesel_price ​REAL,
    // average_inssurance_price REAL,​unit​ TEXT ,"avg_monthly_pass_price" REAL ,​country_code​ INTEGER -
	private static final String TRANSPORTATION_TABLE_NAME = "transportation";
	   private static final String[] TRANSPORTATION_FIELD_NAME = { "_id", "avg_car_price", "avg_gas_price", "avg_diesel_price", "avg_inssurance_price", "unit" ,"avg_monthly_pass_price", "country_code" };
	    private static final String[]  TRANSPORTATION_FIELD_TYPE = { "INTEGER PRIMARY KEY", "REAL", "REAL", "REAL", "REAL", "TEXT", "REAL","INTEGER" };
	 // real estate _id PRIMARY KEY INTEGER, ​type ​TEXT, ​average_rent_pric​e
	    // REAL, average_buying_price ​REAL, country_code INTEGER
	    private static final String REAL_ESTATE_TABLE_NAME = "real_estate";
	    private static final String[] REAL_ESTATE_FIELD_NAME = { "_id", "type", "avg_rent_price", "avg_buying_price",
	            "country_code" };
	    private static final String[] REAL_ESTATE_FIELD_TYPE = { "INTEGER PRIMARY KEY", "TEXT", "REAL", "REAL", "INTEGER" };


	// email _id PRIMARY KEY INTEGER, ​name TEXT, ​age INTEGER,
	// climate_preference​ TEXT, ​email TEXT
	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "_id", "name", "email", "password" };
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT" };

	private static final String Japan_TABLE_NAME = "Japan";
	private static final String[] Japan_TABLE_FIELD_NAME =
	{ "_id", "type", "descrption", "unit", "price"};
	private static final String[] Japan_TABLE_FIELD_TYPES = 
	{ "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT" , "INTEGER"};

	private User mCurrentUser;
	
	// this should be a relation table. we will add the id of countries that
	// mCurrentUser looked up?
	private DBModel mUserDB;

	private DBModel mUserChoosenCountryDB;
	// DBModel veriable for each country

	private DBModel mJapanDB;
	private DBModel mUSADB;
	private DBModel mSpainDB;
	private DBModel mUKDB;
	private DBModel mCountryDB;
    //Each one of these represents a table for each country
	private DBModel mUSAFood;
	private DBModel mUSATransportation;
	private DBModel mUSARealEstate;

	private DBModel mUKFood;
    private DBModel mUKTransportation;
    private DBModel mUKRealEstate;

    private DBModel mSpainFood;
    private DBModel mSpainTransportation;
    private DBModel mSpainRealEstate;


    private DBModel mBrazilFood;
    private DBModel mBrazilTransportation;
    private DBModel mBrazilRealEstate;

    private DBModel mVietnamFood;
    private DBModel mVietnamTransportation;
    private DBModel mVietnamRealEstate;

	private ObservableList<User> mAllUsersList;
	private ObservableList<Country> mAllCountiresList;
	private ObservableList<Grocery> mAllGroceriesList;
	private ObservableList<Transportation> mAllTransportationList;
	private ObservableList<Housing> mAllHousingList;

	public static ObservableList<Grocery> finalGroceryList  = FXCollections.observableArrayList();
	public static ObservableList<Transportation> finalTransportationList = FXCollections.observableArrayList();
	public static ObservableList<Housing> finalHousingList = FXCollections.observableArrayList();

	private Controller() {
	}

	private static void initDBModelObjects() throws SQLException {
		// USA DBModel
		controller.mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
		controller.mUSAFood = new DBModel(DB_NAME, FOOD_TABLE_NAME, FOOD_TABLE_FIELD_NAME,
				FOOD_TABLE_FIELD_TYPE);
		
		controller.mUSATransportation = new DBModel(DB_NAME, TRANSPORTATION_TABLE_NAME,
				TRANSPORTATION_FIELD_NAME, TRANSPORTATION_FIELD_TYPE);
		
		controller.mUSARealEstate = new DBModel(DB_NAME, REAL_ESTATE_TABLE_NAME, REAL_ESTATE_FIELD_NAME,
				REAL_ESTATE_FIELD_TYPE);
		// Japan DBModel
		controller.mJapanDB = new DBModel(DB_NAME, Japan_TABLE_NAME, Japan_TABLE_FIELD_NAME, Japan_TABLE_FIELD_TYPES);
		// Countries 
		controller.mUserChoosenCountryDB = new DBModel(DB_NAME,COUNTRY_TABLE_NAME,COUNTRY_TABLE_FIELD_NAME,COUNTRY_TABLE_FIELD_TYPES);
	}
	private static void  initAllListsFX()
	{
		controller.mAllUsersList = FXCollections.observableArrayList();
		controller.mAllCountiresList = FXCollections.observableArrayList();
		controller.mAllGroceriesList = FXCollections.observableArrayList();
		controller.mAllHousingList = FXCollections.observableArrayList();
		controller.mAllTransportationList= FXCollections.observableArrayList();
	}

	private static void addUserToList() throws SQLException
	{
		ArrayList<ArrayList<String>> resultsList = controller.mUserDB.getAllRecords();

		for (ArrayList<String> values : resultsList) {
			int id = Integer.parseInt(values.get(0));
			String name = values.get(1);
			String email = values.get(2);
			String role = values.get(3);
			controller.mAllUsersList.add(new User(id, name, email, role));
		}
	}
	
	private static void addUSAToLists() throws SQLException
	{
		ArrayList<ArrayList<String>> resultsList = controller.mUSAFood.getAllRecords();
		String description, unit;
		double price;
		Types dairy = Types.Dairy_products;
		Types fruit = Types.Fruit;
		Types vegi = Types.Vegetable;
		for (ArrayList<String> f : resultsList) {
			Types type = Types.Dairy_products;
			if (f.get(1).equalsIgnoreCase("Fruit"))
				type = fruit;
			if (f.get(1).equalsIgnoreCase("Dairy"))
				type = dairy;
			if (f.get(1).equalsIgnoreCase("Vegetables"))
				type = vegi;

			description = f.get(2);
			unit = f.get(3);
			price = Double.valueOf(f.get(4));
			Grocery g = new Grocery(description, unit, price, Integer.parseInt(USA_COUNTRY_CODE), type);
			controller.mAllGroceriesList.add(g);
		}

		resultsList = controller.mUSATransportation.getAllRecords();
		double average_economic_car_price, average_gas_price, avgDiesel, average_inssurance_price,
				avgMonthlyPass;
		for (ArrayList<String> f : resultsList) { // type,rent buying
			average_economic_car_price = Double.valueOf(f.get(1));
			average_gas_price = Double.valueOf(f.get(2));
			avgDiesel = Double.valueOf(f.get(3));
			average_inssurance_price = Double.valueOf(f.get(4));
			avgMonthlyPass = Double.valueOf(f.get(6));
			controller.mAllTransportationList.add(new Transportation(average_economic_car_price,
					average_gas_price, avgDiesel, average_inssurance_price, Types.G, avgMonthlyPass,
					Integer.valueOf(USA_COUNTRY_CODE)));
			
		}
		resultsList = controller.mUSARealEstate.getAllRecords();
		String type;
		double avgBuyPrice, avgRentPrice;
		for (ArrayList<String> f : resultsList) { // type,rent buying
			type = f.get(1);
			avgBuyPrice = Double.valueOf(f.get(2));
			avgRentPrice = Double.valueOf(f.get(4));
			controller.mAllHousingList
					.add(new Housing(type, avgBuyPrice, avgRentPrice, Integer.valueOf(USA_COUNTRY_CODE)));
		}
	}
	
	private static void addToAllCountriesList() throws SQLException
	{
		controller.initializeAllCountriesDBFromFile();
		
		ArrayList<ArrayList<String>> country = controller.mUserChoosenCountryDB.getAllRecords();
		String name, climite;
		int population;
		for (ArrayList<String> c : country)
		{
			name = c.get(1);
			population = Integer.valueOf(c.get(2));
			climite = c.get(3);
			controller.mAllCountiresList.add(new Country(name,climite,population));
		}
	}
	
    private int initializeAllCountriesDBFromFile() throws SQLException 
	{
		int recordsCreated = 0;
		if (controller.mUserChoosenCountryDB.getRecordCount() > 0)
			return 0;
		try {
			// Otherwise, open the file (CSV file) and insert user data
			// into database
			Scanner fileScanner = new Scanner(new File(AllCountries));
			// First read is for headings:
			fileScanner.nextLine();
			// All subsequent reads are for user data
			while (fileScanner.hasNextLine()) {
				String[] data = fileScanner.nextLine().split(",");
				// Length of values is one less than field names because values
				// does not have id (DB will assign one)
				String[] values = new String[COUNTRY_TABLE_FIELD_NAME.length - 1];
				System.out.println(Arrays.toString(data));
				values[0] = data[0];
				values[1] = data[1];
				values[2] = data[3];
				controller.mUserChoosenCountryDB.createRecord(Arrays.copyOfRange(COUNTRY_TABLE_FIELD_NAME, 1, COUNTRY_TABLE_FIELD_NAME.length), values);
				recordsCreated++;
			}

			// All done with the CSV file, close the connection
			fileScanner.close();
		} catch (FileNotFoundException e) {
			return 0;
		}
		return recordsCreated;
	}

	private static void addJapanToLists() throws SQLException
	{
		//TODO Japan Stuff 
		controller.initializeJapanDBFromFile();
		ArrayList<ArrayList<String>> JapanSet = controller.mJapanDB.getAllRecords();
		int country = Integer.valueOf(JAPAN_COUNTRY_CODE);
		String description,unit;
		double price;
		for (ArrayList<String> values : JapanSet)
		{
			 description = values.get(2);
			 unit = values.get(3);
			 price = Double.valueOf(values.get(4));
			Types t = Types.valueOf(values.get(1));
			if(t.equals(Types.Fruit ) || t.equals(Types.Vegetable) || t.equals(Types.Meat) || t.equals(Types.Dairy))
			controller.mAllGroceriesList.add(new Grocery(description,unit,price,country,t));
			else if(t.equals(Types.Transportation))
				controller.mAllTransportationList.add(new Transportation(description,unit,price,t,country));
			else if(t.equals(Types.RealEstate))
				controller.mAllHousingList.add(new Housing(description,247120,price,country));
		}
	}

	private int initializeJapanDBFromFile() throws SQLException {
		int recordsCreated = 0;
		// If the result set contains results, database table already has
		// records, no need to populate from file (so return false)
		if (controller.mJapanDB.getRecordCount() > 0)
			return 0;
		try {
			// Otherwise, open the file (CSV file) and insert user data
			// into database
			Scanner fileScanner = new Scanner(new File(Japan_FILE_DATA_FILE));
			// First read is for headings:

			fileScanner.nextLine();
			// All subsequent reads are for user data
			while (fileScanner.hasNextLine()) {
				String[] data = fileScanner.nextLine().split(",");
				// Length of values is one less than field names because values
				// does not have id (DB will assign one)
				String[] values = new String[ Japan_TABLE_FIELD_NAME.length - 1];
				System.out.println(Arrays.toString(data));
				values[0] = data[0];
				values[1] = data[1];
				values[2] = data[2];
				values[3] = data[3];
				controller.mJapanDB.createRecord(Arrays.copyOfRange(Japan_TABLE_FIELD_NAME, 1, Japan_TABLE_FIELD_NAME.length), values);
				recordsCreated++;
			}

			// All done with the CSV file, close the connection
			fileScanner.close();
		} catch (FileNotFoundException e) {
			return 0;
		}
		return recordsCreated;
	}

	public static Controller getInstance() {
		if(controller == null) {
			controller = new Controller();
			initAllListsFX();
			try {
				// Crete all the tables in the database
				initDBModelObjects();
				// Create a user object and added to allUsersList
				addUserToList();
				// Read from CSV files to and add to USA tables
				controller.initializeUSA();
				// Create usa object reading from USA tables
				addUSAToLists();
				// Handle all Japan stuff
				 addJapanToLists();
				// Create the countries list
				 addToAllCountriesList();
			}catch (SQLException e)
			{e.printStackTrace();}
		}
		return controller;
	}
	
	public boolean isValidPassword(String password) {
		// Valid password must contain (see regex below):
		// At least one digit
		// At least one lower case letter
		// At least one upper case letter
		// At least one special character !@#$%^&*()_+\-=[]{};':"\|,.<>/?
		// At least 8 characters long, but no more than 16
		return password.matches(
				"^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\\\|,.<>\\/?]).{8,16}$");
	}

	public boolean isValidEmail(String email) {
		return email.matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

	public String signUpUser(String name, String email, String password) {
		// Check email to see if valid
		if (!isValidEmail(email))
			return "Email address not valid.  Please try different address.";

		// Check to see if email is already used
		// Loop through all users list
		for (User u : controller.mAllUsersList)
			if (email.equalsIgnoreCase(u.getEmail()))
				return "Email address already used.  Please sign in or use different address.";

		// Check password to see if valid
		// if (!isValidPassword(password))
		// return "Password must be at least 8 characters, including 1 upper
		// case letter, 1 number and 1 symbol.";

		// Made it through all the checks, create the new user in the database
		String[] values = { name, email, password };
		// Insert the new user into the database
		try {
			// Store the new id
			int id = controller.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length),
					values);
			// Save the new user as the current user
			controller.mCurrentUser = new User(id, name, email, "user");
			// Add the new user to the observable list
			controller.mAllUsersList.add(controller.mCurrentUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error creating user, please try again.";
		}

		return "SUCCESS";
	}

	public String signInUser(String email, String password) {
		// TODO: Implement this method
		// Loop through the list of all users
		for (User u : controller.mAllUsersList) {
			if (u.getEmail().equalsIgnoreCase(email)) {
				// Go into database to retrieve password:
				try {
					ArrayList<ArrayList<String>> userResults = controller.mUserDB.getRecord(String.valueOf(u.getId()));
					String storedPassword = userResults.get(0).get(3);
					// Check the passwords
					if (password.equals(storedPassword)) {
						mCurrentUser = u;
						return "SUCCESS";
					} else
						break;

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return "Incorrect email or password.";
	}
	
	public User getCurrentUser() {
		return mCurrentUser;
	}

	public ObservableList<User> getAllUsers() {
		return controller.mAllUsersList;
	}

	public ObservableList<Country> getAllCountriesList() {
		return controller.mAllCountiresList;
	}

	public ObservableList<Grocery> getAllGroceries() {
		return controller.mAllGroceriesList;
	}

	public ObservableList<Transportation> getAllTransportation() {
		return controller.mAllTransportationList;
	}

	public ObservableList<Housing> getAllHousing() {
		return controller.mAllHousingList;
	}

    private int initializeUSA() throws SQLException
    {
        int recordsCreated = 0;

        if (controller.mUSAFood.getRecordCount() > 0 &&

                controller.mUSATransportation.getRecordCount() > 0 && controller.mUSARealEstate.getRecordCount() > 0)
            return 0;

        //DAIRY
        try
        {System.out.println("inside Dairy try");
            Scanner fileScanner = new Scanner(new File(USA_DAIRY_FILE_DATA_FILE));
            fileScanner.nextLine();

            while (fileScanner.hasNextLine())
            {
                String[] data = fileScanner.nextLine().split(",");
                // Length of values is one less than field names because values
                // does not have id (DB will assign one)
                String[] values = new String[FOOD_TABLE_FIELD_NAME.length - 1];

                values[0] = data[0];
                values[1] = data[1];
                values[2] = data[2];
                values[3] = data[3];
                values[4] = data[4];

                controller.mUSAFood.createRecord(
                        Arrays.copyOfRange(FOOD_TABLE_FIELD_NAME, 1, FOOD_TABLE_FIELD_NAME.length), values);
                recordsCreated++;
            }

            fileScanner.close();
        }
        catch (FileNotFoundException e)
        {   System.out.println("inside cairy catch");
            return 0;
        }

        //FRUIT
        try {
            Scanner fileScanner = new Scanner(new File(USA_FRUIT_DATA_FILE));
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                // Length of values is one less than field names because values
                // does not have id (DB will assign one)
                String[] values = new String[FOOD_TABLE_FIELD_NAME.length - 1];
                values[0] = data[0];
                values[1] = !data[2].equals("") ? data[1] :data[1] +", " + data[2];
                values[2] = data[5];
                values[3] = data[7];
                values[4] = USA_COUNTRY_CODE;
                // look at fruits USA FIle

                controller.mUSAFood.createRecord(
                        Arrays.copyOfRange(FOOD_TABLE_FIELD_NAME, 1, FOOD_TABLE_FIELD_NAME.length), values);
                recordsCreated++;
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("fruit catch");
            return 0;
        }


        //MEAT
        try {
        	System.out.println("In the Meat Try");
            Scanner fileScanner = new Scanner(new File(USA_MEAT_DATA_FILE));
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                // Length of values is one less than field names because values
                // does not have id (DB will assign one)
                String[] values = new String[FOOD_TABLE_FIELD_NAME.length - 1];
                // "_id", "type", "description", "unit", "price", "country_code
                // "
                // type description lb price
                values[0] = data[0];
                values[1] = data[1];
                values[2] = data[2];
                values[3] = data[3];
                values[4] =USA_COUNTRY_CODE;
                controller.mUSAFood.createRecord(
                        Arrays.copyOfRange(FOOD_TABLE_FIELD_NAME, 1, FOOD_TABLE_FIELD_NAME.length), values);

                recordsCreated++;
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
        	System.out.println("Meat Catch");
            return 0;
        }

        //Vegetables
        try {
        	System.out.println("In the veggi try");
            Scanner fileScanner = new Scanner(new File(USA_VEGETABLE_DATA_FILE));
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                // Length of values is one less than field names because values
                // does not have id (DB will assign one)
                String[] values = new String[FOOD_TABLE_FIELD_NAME.length - 1];
                // "_id", "type", "description", "unit", "price", "country_code
                // "
                // type description lb price
                values[0] = data[2];
                values[1] = !data[4].equals("") ? data[3] : data[3]+ ", " + data[4];
                values[2] = data[7];
                values[3] = data[9];
                values[4] = USA_COUNTRY_CODE;
                controller.mUSAFood.createRecord(
                        Arrays.copyOfRange(FOOD_TABLE_FIELD_NAME, 1, FOOD_TABLE_FIELD_NAME.length), values);

                recordsCreated++;
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Veggi catch");
        	return 0;
        }


        //Transportation

        try {
        	System.out.println("In transportation USA");
            Scanner fileScanner = new Scanner(new File(USA_PRIVATE_TRANSPORTATION_FILE));
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                // Length of values is one less than field names because values
                // does not have id (DB will assign one)
                String[] values = new String[TRANSPORTATION_FIELD_NAME.length - 1];
System.out.println(Arrays.toString(data));
                values[0] = data[0];
                values[1] = data[1];
                values[2] = data[2];
                values[3] = data[3];
                values[4] = data[4];
                values[5] = data[5];
                values[6] =USA_COUNTRY_CODE;
//average_economic_car_price,
                //average_gas_price,
                //double avgDiesel,
                //double average_inssurance_price,
                //Types unit,
                //double avgMonthlyPass,
                //int country_code) {

                controller.mUSATransportation.createRecord(Arrays.copyOfRange(TRANSPORTATION_FIELD_NAME, 1,
                        TRANSPORTATION_FIELD_NAME.length), values);
                recordsCreated++;
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            	System.out.println("Transportation catch");
        	return 0;
        }


        //Real Estate
        try {
            Scanner fileScanner = new Scanner(new File(USA_HOUSING_FILE));
            fileScanner.nextLine();
            System.out.println("in real estat USA");
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                // Length of values is one less than field names because values
                // does not have id (DB will assign one)
                String[] values = new String[REAL_ESTATE_FIELD_NAME.length - 1];

                values[0] = data[0];
                values[1] = data[1];
                values[2] = data[2];
                values[3] = data[3];

                controller.mUSARealEstate.createRecord(
                        Arrays.copyOfRange(REAL_ESTATE_FIELD_NAME, 1, REAL_ESTATE_FIELD_NAME.length), values);
                recordsCreated++;
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("USA Real State Catch");
        	return 0;
        }
        return recordsCreated;
    }

private int initializeEU()throws SQLException{
    int recordsCreated=0;

    //DAIRY
    try
    {System.out.println("inside Dairy try");
        Scanner fileScanner = new Scanner(new File(EU_DAIRY_DATA_FILE));
        fileScanner.nextLine();
        fileScanner.nextLine();fileScanner.nextLine();fileScanner.nextLine();fileScanner.nextLine();
        ArrayList<Grocery> groceriesSp = new ArrayList<>();
      FileScanner:  while (fileScanner.hasNextLine())
        {
            String[] data = fileScanner.nextLine().split(",");
            // Length of values is one less than field names because values
            // does not have id (DB will assign one)
            String[] values = new String[FOOD_TABLE_FIELD_NAME.length - 1];
            values[0]=data[0];//type
            values[1]=data[4];//description
            values[2]=data[5];//unit
            values[3]=data[8];//price
        //_id", "type", "description", "unit", "price", "country_code" };
       //description, String unit, double price, int country_code, Types typ

            if(data[6].equalsIgnoreCase("ES")){


                    groceriesSp.add(new Grocery(data[4], data[5], Double.valueOf(data[8]),
                            Integer.valueOf(SPAIN_COUNTRY_CODE), Types.Dairy_products));
                    for (Grocery e : groceriesSp)
                    {
                        if (e.getDescription().equalsIgnoreCase(data[4])) break FileScanner;
                    }

                controller.mSpainFood.createRecord(
                        Arrays.copyOfRange(FOOD_TABLE_FIELD_NAME, 1, FOOD_TABLE_FIELD_NAME.length), values);
            }
            if (data[6].equalsIgnoreCase("UK")){
                ArrayList<Grocery> groceriesUK = new ArrayList<>();
                groceriesUK.add(new Grocery(data[4],data[5],Double.valueOf(data[8]), Integer.valueOf(SPAIN_COUNTRY_CODE), Types.Dairy_products ));
                controller.mUKFood.createRecord(
                        Arrays.copyOfRange(FOOD_TABLE_FIELD_NAME, 1, FOOD_TABLE_FIELD_NAME.length), values);
            }

            recordsCreated++;
        }

        fileScanner.close();
    }
    catch (FileNotFoundException e)
    {   System.out.println("inside cairy catch");
        return 0;
    }


return recordsCreated;
}

	public boolean addToFinalList(Transportation selected) 
	{
		if(!finalTransportationList.contains(selected))
		{
			finalTransportationList.add(selected);
			return true;
		}
		return false;
	}

	public boolean addToFinalList(Housing selected) 
	{
	
		if(!finalHousingList.contains(selected))
		{
			finalHousingList.add(selected);
			return true;
		}
		return false;
	}
	
	public boolean addToFinalList(Grocery selected) 
	{
		if(!finalGroceryList.contains(selected)) 
		{
			finalGroceryList.add(selected);
			return true;
		}
		return false;
	}
	

}
