package com.mum.edu.library.ui.checkout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CheckoutBookWindow extends Application{
	private static CheckoutBookWindow instance;
	
	public static CheckoutBookWindow getInstance(){
		if(instance == null){
			instance = new CheckoutBookWindow();
		}
		
		return instance;
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CheckoutBookWindow.fxml"));
        primaryStage.setTitle("Checkout Book");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1000, 520));
        primaryStage.show();
    }

	
    public static void main(String[] args) {	
        launch(args);
    }
    
    public CheckoutBookController getController(){
    	try
    	{
    		FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("CheckoutBookWindow.fxml"));
			fxmlLoader.load();
		
			Object con = fxmlLoader.getController();
			
			return (CheckoutBookController)con;
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    		return null;
    	}
    }
    
}
