package edu.orangecoastcollege.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.orangecoastcollege.controller.Controller;
import edu.orangecoastcollege.model.Country;
import edu.orangecoastcollege.model.Grocery;
import edu.orangecoastcollege.model.Types;
import edu.orangecoastcollege.model.convertions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class FoodScene implements Initializable, convertions {

private static Controller controller = Controller.getInstance();
	
	@FXML
	private ListView<Grocery> foodLV;
	@FXML
	private Label Meat;
	@FXML
	private Label veggi;
	@FXML
	private Label dairy;
	@FXML
	private Label reset;
	@FXML
	private Label fruit;
	
	// Event Listener on Button.onAction
	@FXML
	public void sortFruit() {

		ObservableList<Grocery> filteredList = FXCollections.observableArrayList();
		filteredList = controller
				.filterFood(f -> f.getCountry_code() == controller.countryChoosen && f.getType().equals(Types.Fruit));
		foodLV.setItems(filteredList);
		
	}

	@FXML
	public void sortDairy() {

		ObservableList<Grocery> dairyList = FXCollections.observableArrayList();
		dairyList = controller
				.filterFood(f -> f.getCountry_code() == controller.countryChoosen && f.getType().equals(Types.Dairy));
		foodLV.setItems(dairyList);
		
	}

	@FXML
	public void sortMeat() {
		ObservableList<Grocery> meat = FXCollections.observableArrayList();
		meat = controller
				.filterFood(e -> e.getCountry_code() == controller.countryChoosen && e.getType().equals(Types.Meat));
		foodLV.setItems(meat);
		
	}

	@FXML
	public void sortVeggis() {
		ObservableList<Grocery> veggis = FXCollections.observableArrayList();
		veggis = controller.filterFood(
				e -> e.getCountry_code() == controller.countryChoosen && e.getType().equals(Types.Vegetable));
		foodLV.setItems(veggis);
		
	}

	@FXML
	public void sortReset() {
		ObservableList<Grocery> counrty = FXCollections.observableArrayList();
		for (Grocery g : controller.getAllGroceries()) {
			if (g.getCountry_code() == Integer.valueOf(Controller.countryChoosen))
				counrty.add(g);
		}
		foodLV.setItems(counrty);
		

	}
	@FXML
	public void BackToChooseScene(ActionEvent event) {
		// TODO Autogenerated
        ViewNavigator.loadScene("Categories", ViewNavigator.CHOOSE_SCENE);

	}

	public void addToSaveList(ActionEvent event) {
		
		for (Grocery f : controller.getAllGroceries()) 
		{	if(f.getCountry_code() == Integer.valueOf(Controller.countryChoosen))
			controller.saveList.add(f);

		}

		Controller.generateMessage();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<Grocery> counrty = FXCollections.observableArrayList();
	for(Grocery g:controller.getAllGroceries())
	{
		if(g.getCountry_code() == Integer.valueOf(Controller.countryChoosen))
			counrty.add(g);
	}
		foodLV.setItems(counrty);
	}

	@Override
	public boolean addFinalScene() {
		Grocery selected = foodLV.getSelectionModel().getSelectedItem();
	    // 2) Use the controller to add the selected game to the inventory
	return controller.addToFinalList(selected);
	
	}
}
