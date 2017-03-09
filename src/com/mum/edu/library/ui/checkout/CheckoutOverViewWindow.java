package com.mum.edu.library.ui.checkout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        primaryStage.setScene(new Scene(root, 1142, 664));
        primaryStage.show();
    }

	
    public static void main(String[] args) {	
        launch(args);
    }

}
