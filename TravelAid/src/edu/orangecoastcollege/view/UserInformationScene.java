package edu.orangecoastcollege.view;



import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import edu.orangecoastcollege.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

// TODO: Implement the Initializable interface
public class UserInformationScene implements Initializable {

	@FXML
	TextField ageTF;
	@FXML
	TextField emailTF;
	@FXML
	TextField nameTF;
	@FXML
	TextField locationTF;
	@FXML
	ComboBox<String> climateCB;
	@FXML
	ComboBox<String> languagesCB;
	
	Locale l = Locale.getDefault();

	private static Controller controller = Controller.getInstance();

	
	
	@FXML
	public void clearButton()
	{	
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<String> climate = FXCollections.observableArrayList();
		climate.add("Mediterranean");
		climate.add("Polar");
		climate.add("Temperate Regions");
		climate.add("Tropical");
		ObservableList<String> languages = FXCollections.observableArrayList();
		languages.add("Ar-Arabic");
		languages.add("En-English");
		languages.add("Fr-French");
		languages.add("Sp-Spain");
		languages.add("Vt-vietnam");
		climateCB.setItems(climate);
		languagesCB.setItems(languages);
		 userLocation();
	}

}
