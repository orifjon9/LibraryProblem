package com.mum.edu.library.ui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Welcome extends Stage {
	public static final Welcome INSTANCE = new Welcome();
	Stage primaryStage;
	
	private Welcome() {}
	
	public void setStage(Stage ps) {
		primaryStage = ps;
		primaryStage.setTitle("Welcome Page");
				
		VBox topContainer = new VBox();
		MenuBar mainMenu = new MenuBar();
		Text label = new Text("WELCOME");
		label.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 60));
		label.setFill(Color.DARKRED);
		HBox labelBox = new HBox(10);
		labelBox.setAlignment(Pos.CENTER);
		labelBox.getChildren().add(label);
		
		topContainer.getChildren().add(mainMenu);
		topContainer.getChildren().add(labelBox);

		Menu custMenu = new Menu("Librarian");
		MenuItem checkoutBook = new MenuItem("Checkout Book");
		checkoutBook.setOnAction(evt -> {	
//			catalogs.setStage(ps);
//	        catalogs.setData(DefaultData.CATALOG_LIST_DATA);
//	        catalogs.show();  
//	        ps.hide();
					
		});
		MenuItem exitApp = new MenuItem("Exit");
		exitApp.setOnAction(evt -> Platform.exit());
			
		custMenu.getItems().addAll(checkoutBook, exitApp);
		
		MenuItem exitApp2 = new MenuItem("Exit");
		exitApp.setOnAction(evt -> Platform.exit());

		//did not add menu item for menu edit
		Menu adminMenu = new Menu("Administrator");
		MenuItem addNewBook = new MenuItem("Add Book");
		MenuItem createLibraryMember = new MenuItem("Create Member");
		createLibraryMember.setOnAction(evt -> {
			AddLibraryMember catalogListWindow = AddLibraryMember.INSTANCE;
			catalogListWindow.setStage(primaryStage);
		});
		
		MenuItem editLibraryMember = new MenuItem("Edit Member");
		adminMenu.getItems().addAll(addNewBook,createLibraryMember,editLibraryMember, exitApp2);
 		mainMenu.getMenus().addAll(custMenu, adminMenu);
 		
 		//must have to show
		primaryStage.setScene(new Scene(topContainer, 800, 400));
		primaryStage.show();
	}
	
}
