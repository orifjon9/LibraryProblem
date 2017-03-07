package com.mum.edu.library.ui;

import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.dao.impl.MemberDAOImpl;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Member;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddLibraryMember extends Stage {
	public static final AddLibraryMember INSTANCE = new AddLibraryMember();
	Stage primaryStage;

	private AddLibraryMember() {
	}

	public void setStage(Stage ps) {
		primaryStage = ps;
		primaryStage.setTitle("Add Library Member");
		
		VBox topContainer = new VBox();
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPrefHeight(520);
		
		MenuBar mainMenu = new MenuBar();

		// ---------------MemberID------------
		Label memberIDlbl = new Label("MemberID:");
		memberIDlbl.getStyleClass().add("member-label");
		grid.add(memberIDlbl, 1, 1);

		TextField memberID = new TextField();
		grid.add(memberID, 2, 1);

		// ---------------First Name------------
		Label firstNamelbl = new Label("FirstName:");
		firstNamelbl.getStyleClass().add("member-label");
		grid.add(firstNamelbl, 1, 2);

		TextField firstName = new TextField();
		firstName.setPrefWidth(400);
		grid.add(firstName, 2, 2);

		// ---------------Last Name------------
		Label lastNamelbl = new Label("Last Name:");
		lastNamelbl.getStyleClass().add("member-label");
		grid.add(lastNamelbl, 1, 3);

		TextField lastName = new TextField();
		lastName.setPrefWidth(400);
		grid.add(lastName, 2, 3);
		// ---------------Street------------
		Label streetlbl = new Label("Street:");
		streetlbl.getStyleClass().add("member-label");
		grid.add(streetlbl, 1, 4);

		TextField street = new TextField();
		street.setPrefWidth(400);
		grid.add(street, 2, 4);
		// ---------------City------------
		Label citylbl = new Label("City:");
		citylbl.getStyleClass().add("member-label");
		grid.add(citylbl, 1, 5);

		TextField city = new TextField();
		city.setPrefWidth(400);
		grid.add(city, 2, 5);
		// ---------------State------------
		Label statelbl = new Label("State:");
		statelbl.getStyleClass().add("member-label");
		grid.add(statelbl, 1, 6);

		TextField state = new TextField();
		state.setPrefWidth(400);
		grid.add(state, 2, 6);
		// ---------------Zip------------
		Label ziplbl = new Label("Zip:");
		ziplbl.getStyleClass().add("member-label");
		grid.add(ziplbl, 1, 7);

		TextField zip = new TextField();
		zip.setPrefWidth(400);
		grid.add(zip, 2, 7);
		// ---------------Telephone Number------------
		Label phonelbl = new Label("PhoneNumber:");
		phonelbl.getStyleClass().add("member-label");
		grid.add(phonelbl, 1, 8);

		TextField phone = new TextField();
		phone.setPrefWidth(400);
		grid.add(phone, 2, 8);

		Button btn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 2, 9);
		grid.setId("grid");


		btn.setOnAction(avt -> {
			MemberDAO memberDAO = new MemberDAOImpl();
			Member member = new Member(Integer.parseInt(memberID.getText()), firstName.getText(), lastName.getText(),
					new Address(street.getText(), city.getText(), state.getText(), zip.getText()), phone.getText());
			memberDAO.save(member);
		});
		
		Menu home = new Menu("Home");
		MenuItem back = new MenuItem("Back");
		MenuItem logout = new MenuItem("Logout");
		MenuItem exit = new MenuItem("Exit");
		home.getItems().addAll(back,logout,exit);
		mainMenu.getMenus().addAll(home);
		
		back.setOnAction(evt -> {
			Welcome welcome = Welcome.INSTANCE;
			welcome.setStage(primaryStage);
		});
		
		exit.setOnAction(evt -> Platform.exit());
		
		logout.setOnAction(evt -> {
			Login login = Login.INSTANCE;
			login.start(primaryStage);
		});
		
		topContainer.getChildren().addAll(mainMenu,grid);

		primaryStage.setScene(new Scene(topContainer, 1000, 520));
		primaryStage.getScene().getStylesheets().add(getClass().getResource("addMember.css").toExternalForm());
		primaryStage.show();
	}

}
