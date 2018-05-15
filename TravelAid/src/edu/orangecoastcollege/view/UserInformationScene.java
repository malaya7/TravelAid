package edu.orangecoastcollege.view;



import edu.orangecoastcollege.controller.Controller;
import javafx.fxml.FXML;

// TODO: Implement the Initializable interface
public class UserInformationScene {

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
     /*   nameTF.clear();
        this.mNumOfYearsTF.clear();
        this.mloanAmtTF.clear();
        this.mMonthlyPaymentTF.clear();
        this.mTotalPaymentTF.clear();

        // Reset the fouces back to intreast rate
        mIntrestRateTF.requestFocus();
*/
	}
	
	@FXML
	public void singOutButton()
	{
		// COMPLETED 
		ViewNavigator.loadScene("Welcome to TravelAid", ViewNavigator.SIGN_IN_SCENE);
	}

}
