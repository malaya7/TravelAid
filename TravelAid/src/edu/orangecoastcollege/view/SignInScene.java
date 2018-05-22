package edu.orangecoastcollege.view;


import edu.orangecoastcollege.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignInScene {

	private static Controller controller = Controller.getInstance();

	@FXML
	private TextField emailAddressTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private Label emailErrorLabel;
	@FXML
	private Label passwordErrorLabel;
	@FXML
	private Label signInErrorLabel;


	@FXML
	public boolean signIn() {
	    // Let's read information from the user
	    String email = emailAddressTF.getText();
	    String password = passwordTF.getText();

	    // Let's check to see if the error labels should be made visible:
	    emailErrorLabel.setVisible(email.isEmpty());
	    passwordErrorLabel.setVisible(password.isEmpty());

	    // If either one is visible, return false
	    if (emailErrorLabel.isVisible() || passwordErrorLabel.isVisible())
	        return false;

	    // Let's try to sign the user in (store the result)
	    String result = controller.signInUser(email, password);
	    if (result.equalsIgnoreCase("SUCCESS")) {
			signInErrorLabel.setVisible(false);
			ViewNavigator.loadScene("Choose a Country", ViewNavigator.COUNTRY_CITY_SCENE);
			return true;
		}
		signInErrorLabel.setText(result);
		signInErrorLabel.setVisible(true);
		return false;
	}

	@FXML
	public void loadSignUp(MouseEvent event)
	{
	    ViewNavigator.loadScene("Sign Up", ViewNavigator.SIGN_UP_SCENE);
	}
	public void exitButton()
	{
	    emailAddressTF.clear();
	    passwordTF.clear();
	    emailAddressTF.requestFocus();
	}
}
