package edu.orangecoastcollege.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import edu.orangecoastcollege.controller.Controller;
import edu.orangecoastcollege.model.Country;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Emailu implements Initializable{
	@FXML
	private Label emailErrorLabel;
	@FXML
	private TextField getUserName;
	@FXML
	private TextField getEmail;
	@FXML
	ListView<Country> countriesLV;
	@FXML
	private TextArea emailMessageDisplay;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		countriesLV.setItems(controller.getAllCountriesList());
		//if(controller.getCurrentUser().getEmail()!= null  && controller.getCurrentUser().getName()!= null){
		controller.generateMessage();
		getEmail.setText(controller.getCurrentUser().getEmail());
		getUserName.setText(controller.getCurrentUser().getName());
		emailMessageDisplay.setWrapText(true);
		emailMessageDisplay.textProperty().set(controller.getEmailMessage());
		//}
	}

	private Controller controller = Controller.getInstance();

	// Event Listener on Button.onAction
	@FXML
	public void sendEmailFromView(ActionEvent event) {

		try {
			controller.sendEmail();
		} catch (MessagingException e) {
			emailErrorLabel.setVisible(true);
		}
		
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void goBackButton(ActionEvent event) {
	      
		ViewNavigator.loadScene("choose category", ViewNavigator.CHOOSE_SCENE);
	}
	
	@FXML
	public void showStats(ActionEvent event) {
	      
		ViewNavigator.loadScene("Stats", ViewNavigator.STATS);
	}

	
}
