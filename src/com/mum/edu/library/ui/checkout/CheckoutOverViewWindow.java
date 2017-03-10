package com.mum.edu.library.ui.checkout;

import java.util.Set;

import com.mum.edu.library.model.Role;
import com.mum.edu.library.ui.LoginScreen;
import com.mum.edu.library.ui.MainScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CheckoutOverViewWindow extends Application {

private static CheckoutOverViewWindow instance;
	
	public static CheckoutOverViewWindow getInstance(){
		if(instance == null){
			instance = new CheckoutOverViewWindow();
		}
		
		return instance;
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CheckoutOverView.fxml"));
        primaryStage.setTitle("Checkout Records");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1000, 520));
        primaryStage.show();
    }

	
    public static void main(String[] args) {	
        launch(args);
    }

}
