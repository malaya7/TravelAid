package edu.orangecoastcollege.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import edu.orangecoastcollege.model.Country;
import edu.orangecoastcollege.model.DBModel;
import edu.orangecoastcollege.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

	private static Controller theOne;

	private static final String DB_NAME = "vg_inventory.db";

	private static final String USER_TABLE_NAME = "user";
	private static final String[] USER_FIELD_NAMES = { "_id", "name", "email", "role", "password"};
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "TEXT"};

	private static final String VIDEO_GAME_TABLE_NAME = "video_game";
	private static final String[] VIDEO_GAME_FIELD_NAMES = { "_id", "name", "platform", "year", "genre", "publisher"};
	private static final String[] VIDEO_GAME_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "INTEGER", "TEXT", "TEXT"};
	private static final String VIDEO_GAME_DATA_FILE = "videogames_lite.csv";

	// Below is the relationship table "user_games" which associates users with the video games in their inventory
	private static final String USER_GAMES_TABLE_NAME = "user_games";
	private static final String[] USER_GAMES_FIELD_NAMES = { "user_id", "game_id"};
	private static final String[] USER_GAMES_FIELD_TYPES = { "INTEGER", "INTEGER"};

	private User mCurrentUser;
	private DBModel mUserDB;


	private ObservableList<User> mAllUsersList;
	private ObservableList<Country> mAllCountriesList;

	private Controller() {
	}

	public static Controller getInstance() {
		if (theOne == null) {
			theOne = new Controller();
			theOne.mAllUsersList = FXCollections.observableArrayList();
			theOne.mAllCountriesList = FXCollections.observableArrayList();

			try {
				// Create the user table in the database
				theOne.mUserDB = new DBModel(DB_NAME, USER_TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
				ArrayList<ArrayList<String>> resultsList = theOne.mUserDB.getAllRecords();
				for (ArrayList<String> values : resultsList)
				{
					int id = Integer.parseInt(values.get(0));
					String name = values.get(1);
					String email = values.get(2);
					String role = values.get(3);
					theOne.mAllUsersList.add(new User(id, name, email, role));
				}



			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return theOne;
	}

	public boolean isValidPassword(String password)
	{
		// Valid password must contain (see regex below):
		// At least one lower case letter
		// At least one digit
		// At least one special character (@, #, $, %, !)
		// At least one upper case letter
		// At least 8 characters long, but no more than 16
		return password.matches("((?=.*[a-z])(?=.*d)(?=.*[@#$%!])(?=.*[A-Z]).{8,16})");
	}

	public boolean isValidEmail(String email)
	{
		return email.matches(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

	/*public String signUpUser(String name, String email, String password)
	{
		//TODO: Implement this method
	    // check email to see if valid
	    if(!isValidEmail(email))
	        return "Email is not valid";

	    //chek if the email is already used
	    // loop through all users list
	    for(User u : theOne.mAllUsersList)
	        if(email.equalsIgnoreCase(u.getEmail()))
	            return "Email address already used. please sign in or use diffrent email";

	    // check to see if the password is valid
        //if(! isValidPassword(password))
          // return "Password shoule be 8 char, 1 upper case, 1 number";

	    //made it through all the checks
	    String values [] = {name, email ,"user", password};

	    // insert the database
	    try
        {

	        int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length), values);
        // save the new user as the current user
	        theOne.mCurrentUser = new User(id, name, email, "user");
	        // Add the user ti the observable list
	        theOne.mAllUsersList.add(theOne.mCurrentUser);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return"Error creating user, try agian.";
        }

		return "SUCCESS";
	}

	public String signInUser(String email, String password) {

	    for(User u : theOne.mAllUsersList)
	        if(u.getEmail().equalsIgnoreCase(email))
	        {
	            // Go to database to retrive password
	            try
                {
                    ArrayList<ArrayList<String>> userResult = theOne.mUserDB.getRecord(String.valueOf(u.getId()));
                    String storedPassword = userResult.get(0).get(4);
                    // check the passwords
                    if(password.equals(storedPassword))
                    {
                        mCurrentUser = u;
                        return "SUCCESS";
                    }
                    else
                       break;
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
	        }
	    return "Incorrect email or password. please try again.";
	}
*/
	 public String signUpUser(String name, String email, String password)
	    {

	        // Check their email address to see if its valid
	   //     if (!isValidEmail(email)) return "email address is not valid please use a different address";
	        // Check if email is taken
	        for (User u : mAllUsersList)
	            if (u.getEmail().equalsIgnoreCase(email))
	                return "Email address already used. Please sign in or use different address";

	        if (!isValidPassword(password))
	            return "Password must be at least 8 characters, including 1 upper case letter, 1 number, adn 1 symbol";
	        // Made it through all the checks to create new user in the database
	        String[] values = { name, email, "user", password };

	        try
	        {
	            int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length),
	                    values);

	            theOne.mCurrentUser = new User(id, name, email, "user");
	            theOne.mAllUsersList.add(theOne.mCurrentUser);
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	            return "Error creating user, please try again";

	        }
	        return "SUCCESS";
	    }

	    public String signInUser(String email, String password)
	    {

	        // Loop throught the list
	        for (User u : theOne.mAllUsersList)
	        {
	            if (u.getEmail().equalsIgnoreCase(email))
	            {
	                try
	                {
	                    // Go into the DataBase and retrieve the password
	                    ArrayList<ArrayList<String>> userResults = theOne.mUserDB.getRecord(String.valueOf(u.getId()));
	                    String stroredPassword = userResults.get(0).get(4);
	                    return "SUCCESS";
	                    // Check the passwords
	                   /* if (password.equals(stroredPassword))
	                    {
	                        mCurrentUser = u;
	                        return " SUCCESS";
	                    }
	                    break;*/
	                }
	                catch (SQLException e)
	                {
	                    System.out.println(e.getSQLState());
	                }
	            }
	        }

	        return "Incorrect email and/or passwrod. Please try again";
	    }



	public User getCurrentUser()
	{
		return mCurrentUser;
	}

	public ObservableList<User> getAllUsers() {
		return theOne.mAllUsersList;
	}

}
