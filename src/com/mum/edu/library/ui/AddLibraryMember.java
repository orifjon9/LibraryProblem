package com.mum.edu.library.ui;

import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.dao.impl.MemberDAOImpl;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Member;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddLibraryMember extends Stage {
	public static final AddLibraryMember INSTANCE = new AddLibraryMember();
	Stage primaryStage;

	private AddLibraryMember() {
	}

	public void setStage(Stage ps) {
		primaryStage = ps;
		primaryStage.setTitle("Add Library Member");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 10, 0, 10));

		// ---------------MemberID------------
		Label memberIDlbl = new Label("MemberID:");
		grid.add(memberIDlbl, 1, 1);

		TextField memberID = new TextField();
		grid.add(memberID, 2, 1);

		// ---------------First Name------------
		Label firstNamelbl = new Label("FirstName:");
		grid.add(firstNamelbl, 1, 2);

		TextField firstName = new TextField();
		grid.add(firstName, 2, 2);

		// ---------------Last Name------------
		Label lastNamelbl = new Label("Last Name:");
		grid.add(lastNamelbl, 1, 3);

		TextField lastName = new TextField();
		grid.add(lastName, 2, 3);
		// ---------------Street------------
		Label streetlbl = new Label("Street:");
		grid.add(streetlbl, 1, 4);

		TextField street = new TextField();
		grid.add(street, 2, 4);
		// ---------------City------------
		Label citylbl = new Label("City:");
		grid.add(citylbl, 1, 5);

		TextField city = new TextField();
		grid.add(city, 2, 5);
		// ---------------State------------
		Label statelbl = new Label("State:");
		grid.add(statelbl, 1, 6);

		TextField state = new TextField();
		grid.add(state, 2, 6);
		// ---------------Zip------------
		Label ziplbl = new Label("Zip:");
		grid.add(ziplbl, 1, 7);

		TextField zip = new TextField();
		grid.add(zip, 2, 7);
		// ---------------Telephone Number------------
		Label phonelbl = new Label("PhoneNumber:");
		grid.add(phonelbl, 1, 8);

		TextField phone = new TextField();
		grid.add(phone, 2, 8);

		Button btn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 2, 9);

		btn.setOnAction(avt -> {
			MemberDAO memberDAO = new MemberDAOImpl();
			Member member = new Member(Integer.parseInt(memberID.getText()), firstName.getText(), lastName.getText(),
					new Address(street.getText(), city.getText(), state.getText(), zip.getText()), phone.getText());
			memberDAO.save(member);
		});

		primaryStage.setScene(new Scene(grid, 800, 400));
		primaryStage.show();
	}

}
