package com.mum.edu.library.ui;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.mum.edu.library.api.CommonAPI;
import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.dao.impl.MemberDAOImpl;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.model.Role;
import com.mum.edu.library.rule.ApplicationException;
import com.mum.edu.library.rule.RuleException;
import com.mum.edu.library.rule.RuleSet;
import com.mum.edu.library.rule.RuleSetFactory;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryMemberActionScreen extends Stage {
	public static final LibraryMemberActionScreen INSTANCE = new LibraryMemberActionScreen();
	private TextField memberID;
	private TextField firstName;
	private TextField lastName;
	private TextField street;
	private TextField city;
	private TextField zip;
	private TextField phone;
	private Member editMember;
	MemberDAO memberDAO;
	Stage primaryStage;

	private ComboBox<String> stateCb;

	private LibraryMemberActionScreen() {
	}

	public void setStage(Stage ps, Set<Role> roles, Member editMember) {
		setEditMember(editMember);
		setStage(ps, roles);
	}

	public void setStage(Stage ps, Set<Role> roles) {
		primaryStage = ps;
		primaryStage.setTitle("Add Library Member");

		ObservableList<String> options = FXCollections.observableArrayList(CommonAPI.getUSState());
		stateCb = new ComboBox<>(options);

		VBox topContainer = new VBox();

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPrefHeight(520);

		MenuBar mainMenu = new MenuBar();
		HBox hBox = new HBox();
		hBox.setPrefWidth(400);
		Label errorMessage = new Label();
		errorMessage.setId("error-message");
		hBox.getChildren().add(errorMessage);

		grid.add(hBox, 2, 0);
		// ---------------MemberID------------
		Label memberIDlbl = new Label("MemberID:");
		memberIDlbl.getStyleClass().add("member-label");
		grid.add(memberIDlbl, 1, 1);

		memberID = new TextField();
		grid.add(memberID, 2, 1);

		// ---------------First Name------------
		Label firstNamelbl = new Label("FirstName:");
		firstNamelbl.getStyleClass().add("member-label");
		grid.add(firstNamelbl, 1, 2);

		firstName = new TextField();
		firstName.setPrefWidth(400);
		grid.add(firstName, 2, 2);

		// ---------------Last Name------------
		Label lastNamelbl = new Label("Last Name:");
		lastNamelbl.getStyleClass().add("member-label");
		grid.add(lastNamelbl, 1, 3);

		lastName = new TextField();
		lastName.setPrefWidth(400);
		grid.add(lastName, 2, 3);
		// ---------------Street------------
		Label streetlbl = new Label("Street:");
		streetlbl.getStyleClass().add("member-label");
		grid.add(streetlbl, 1, 4);

		street = new TextField();
		street.setPrefWidth(400);
		grid.add(street, 2, 4);
		// ---------------City------------
		Label citylbl = new Label("City:");
		citylbl.getStyleClass().add("member-label");
		grid.add(citylbl, 1, 5);

		city = new TextField();
		city.setPrefWidth(400);
		grid.add(city, 2, 5);
		// ---------------State------------
		Label statelbl = new Label("State:");
		statelbl.getStyleClass().add("member-label");
		grid.add(statelbl, 1, 6);

		// state = new TextField();
		stateCb.setPrefWidth(400);
		grid.add(stateCb, 2, 6);
		// ---------------Zip------------
		Label ziplbl = new Label("Zip:");
		ziplbl.getStyleClass().add("member-label");
		grid.add(ziplbl, 1, 7);

		zip = new TextField();
		zip.setPrefWidth(400);
		grid.add(zip, 2, 7);
		// ---------------Telephone Number------------
		Label phonelbl = new Label("PhoneNumber:");
		phonelbl.getStyleClass().add("member-label");
		grid.add(phonelbl, 1, 8);

		phone = new TextField();
		phone.setPrefWidth(400);
		grid.add(phone, 2, 8);

		Button submitBtn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(submitBtn);
		grid.add(hbBtn, 2, 9);
		grid.setId("grid");
		
		
		//---------------------------------------- SUBMIT ACTION--------------------------------------------------
		submitBtn.setOnAction(avt -> {
			processSubmitAction(errorMessage);
		});

		Menu home = new Menu("Home");
		MenuItem back = new MenuItem("Back");
		MenuItem logout = new MenuItem("Logout");
		MenuItem exit = new MenuItem("Exit");
		home.getItems().addAll(back, logout, exit);
		mainMenu.getMenus().addAll(home);
		
		
		//---------------------------------------- BACK ACTION--------------------------------------------------
		back.setOnAction(evt -> {
			processBackAction(roles);
		});

		exit.setOnAction(evt -> Platform.exit());

		logout.setOnAction(evt -> {
			LoginScreen login = LoginScreen.INSTANCE;
			login.start(primaryStage);
		});
		topContainer.getChildren().addAll(mainMenu, grid);

		primaryStage.setScene(new Scene(topContainer, 1000, 520));
		inputValueToEdit(getEditMember());
		primaryStage.getScene().getStylesheets().add(getClass().getResource("addMember.css").toExternalForm());
		primaryStage.show();
	}

	private void processBackAction(Set<Role> roles) {
		LibraryMemberManagementScreen libraryMemberManagementScreen = LibraryMemberManagementScreen.INSTANCE;
		libraryMemberManagementScreen.setStage(primaryStage, roles);
		// we must load data from member xml
		MemberDAO memberDAO = new MemberDAOImpl();
		ObservableList<Member> members = null;
		try {
			members = FXCollections.observableArrayList(memberDAO.loadMembers());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		libraryMemberManagementScreen.setData(members);
	}

	private void processSubmitAction(Label errorMessage) {
		RuleSet ruleSet = RuleSetFactory.getRuleSet(LibraryMemberActionScreen.this);
		try {
			ruleSet.applyRule(LibraryMemberActionScreen.this);
		} catch (RuleException e) {
			errorMessage.setText(e.getMessage());
			return;
		}
		memberDAO = new MemberDAOImpl();
		if (getEditMember() != null && !StringUtils.isBlank(String.valueOf(getEditMember().getMemberId()))) {
			Member memberChanged = setValueChange(getEditMember());
			try {
				memberDAO.edit(memberChanged);
			} catch (ApplicationException e) {
				showErrorDialog(e.getMessage());
				return;
			}
			showInformationDialog("Edit Member Successfully");
		} else {
			Member member = new Member(Integer.parseInt(memberID.getText()), firstName.getText(),
					lastName.getText(),
					new Address(street.getText(), city.getText(), stateCb.getValue(), zip.getText()),
					phone.getText());
			try {
				if (checkExist(member)) {
					showErrorDialog("This MemberID is existing, Please Input Another MemberID");
					return;
				} else {
					memberDAO.save(member);
				}
			} catch (ApplicationException e) {
				showErrorDialog(e.getMessage());
				return;
			}
			String message = "Create New Member Successfuly, Your Member Id is : " +member.getMemberId();
			showInformationDialog(message);
			memberID.clear();
		}
		clearData();
	}

	private void showInformationDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private boolean checkExist(Member memberToSave) throws ApplicationException {
		for(Member member : memberDAO.loadMembers()) {
			if (member.getMemberId() == memberToSave.getMemberId()) {
				return true;
			}
		}
		return false;
	}

	private void clearData() {
		firstName.clear();
		lastName.clear();
		stateCb.setValue("");
		city.clear();
		zip.clear();
		street.clear();
		phone.clear();
	}

	private void showErrorDialog(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Look, an Error Dialog");
		alert.setContentText(message);
		alert.showAndWait();
	}

	private Member setValueChange(Member editMember) {
		editMember.setFirstName(getFirstNameValue());
		editMember.setLastName(getLastNameValue());
		editMember.getAddress().setStreet(getStreetValue());
		editMember.getAddress().setCity(getCityValue());
		editMember.getAddress().setState(getStateValue());
		editMember.getAddress().setZip(getZipValue());
		editMember.setPhoneNumber(getPhoneNumberValue());
		return editMember;
	}

	private void inputValueToEdit(Member editMember) {
		if (editMember == null) {
			return;
		}
		memberID.setText(String.valueOf(editMember.getMemberId()));
		memberID.setDisable(true);
		firstName.setText(editMember.getFirstName());
		lastName.setText(editMember.getLastName());
		street.setText(editMember.getAddress().getStreet());
		city.setText(editMember.getAddress().getCity());
		zip.setText(editMember.getAddress().getZip());
		phone.setText(editMember.getPhoneNumber());
		stateCb.setValue(editMember.getAddress().getState());
	}

	public String getMemberIdValue() {
		return memberID.getText();
	}

	public String getFirstNameValue() {
		return firstName.getText();
	}

	public String getLastNameValue() {
		return lastName.getText();
	}

	public String getStreetValue() {
		return street.getText();
	}

	public String getCityValue() {
		return city.getText();
	}

	public String getStateValue() {
		return stateCb.getValue();
	}

	public String getZipValue() {
		return zip.getText();
	}

	public String getPhoneNumberValue() {
		return phone.getText();
	}

	public Member getEditMember() {
		return editMember;
	}

	public void setEditMember(Member editMember) {
		this.editMember = editMember;
	}

}
