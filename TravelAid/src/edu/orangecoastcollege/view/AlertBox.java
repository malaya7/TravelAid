package edu.orangecoastcollege.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	public static void display(String title, String message)
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(300);
		
		Label l = new Label();
		l.setText(message);
		
		Button cloeseButton = new Button("Close");
		cloeseButton.setOnAction(e -> window.close());
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(l,cloeseButton);
		vbox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
