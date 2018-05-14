package edu.orangecoastcollege.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewNavigator 
{
	public static final String SIGN_UP_SCENE = "SignUpScene.fxml";
	public static final String SIGN_IN_SCENE = "SignInScene.fxml";
	public static final String CHOOSE_SCENE = "ChooseScene.fxml";
	public static final String COUNTRY_CITY_SCENE = "CountryCityScene.fxml";
	public static final String FOOD_SCENE = "FoodScene.fxml";
	public static final String HOUSE_SCENE = "HouseScene.fxml";
	public static final String TRANSPORTATION_SCENE = "TransportationScene.fxml";
	public static final String USER_INFORMATION_SCENE = "UserInformationScene.fxml";
	


	public static Stage mainStage;

	public static void setStage(Stage stage) 
	{
		mainStage = stage;
	}
	public static void loadScene(String title, String sceneFXML) 
	{

		try 
		{
			mainStage.setTitle(title);
			Scene scene = new Scene(FXMLLoader.load(ViewNavigator.class.getResource(sceneFXML)));
			mainStage.setScene(scene);
			mainStage.show();
		} 
		catch (IOException e) 
		{
			System.err.println("Error loading: " + sceneFXML + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
