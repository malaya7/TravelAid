package edu.orangecoastcollege.view;



import java.util.Locale;

import edu.orangecoastcollege.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

// TODO: Implement the Initializable interface
public class UserInformationScene {

	@FXML
	TextField ageTF;
	@FXML
	TextField emailTF;
	@FXML
	TextField nameTF;
	@FXML
	TextField locationTF;
	
	private static Controller controller = Controller.getInstance();

	// TODO: Override the initialize method (let Eclipse generate the method for you)
	// In this method:
	// 1) Set the items of the allVideoGamesLV to all video games from the controller
	// 2) Set the items of the platformsCB to the distinct platforms from the controller
	// 3) Set the items of the publishersCB to the distinct publishers from the controller	
	
	@FXML
	public void clearButton()
	{
		//TODO: Complete this method
		// 1) Get the selected video game from the allVideoGamesLV (use getSelectedItem())
		// 2) Use the controller to add the selected game to the inventory
		// 3) Return the result (as a boolean)
		  //clear all text fields
        nameTF.clear();
        this.ageTF.clear();
        this.emailTF.clear();
        locationTF.clear();


        // Reset the fouces back to intreast rate
        nameTF.requestFocus();

	}
	
	@FXML 
	public void userLocation()
	{
		Locale l = Locale.getDefault();
		locationTF.setText( l.getDisplayCountry());
	}
	@FXML
	public void doneButton()
	{
		ViewNavigator.loadScene("Choose a Country", ViewNavigator.COUNTRY_CITY_SCENE);
	}
	
	
	@FXML
	public void singOutButton()
	{
		// COMPLETED 
		ViewNavigator.loadScene("Welcome to TravelAid", ViewNavigator.SIGN_IN_SCENE);
	}

}
