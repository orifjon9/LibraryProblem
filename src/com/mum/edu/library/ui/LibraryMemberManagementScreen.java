package com.mum.edu.library.ui;

import java.util.Set;

import com.mum.edu.library.model.Member;
import com.mum.edu.library.model.Role;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class LibraryMemberManagementScreen {
	public static final LibraryMemberManagementScreen INSTANCE = new LibraryMemberManagementScreen();

	private TableView<Member> table;
	Stage primaryStage;
	private Member selected;

	public void setData(ObservableList<Member> members) {
		table.setItems(members);
	}

	private LibraryMemberManagementScreen() {
	}

	@SuppressWarnings("unchecked")
	public void setStage(Stage ps, Set<Role> roles) {
		primaryStage = ps;
		primaryStage.setTitle("Member Management");

		VBox topContainer = new VBox();
		topContainer.setSpacing(5);

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(20, 20, 20, 10));
		hBox.setAlignment(Pos.CENTER);
		Label label = new Label("List Member");
		label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		label.setTextFill(javafx.scene.paint.Color.WHITE);
		hBox.getChildren().add(label);

		HBox hBoxTable = new HBox();
		hBoxTable.setPadding(new Insets(0, 20, 0, 20));

		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonBox.setPadding(new Insets(10, 20, 0, 0));

		Button editButton = new Button("Edit");
		editButton.setAlignment(Pos.CENTER);
		editButton.setId("button-edit");
		editButton.setPrefWidth(100);
		
		editButton.setOnAction(evt -> {
			selected = table.getSelectionModel().getSelectedItem();
			if (selected != null) {
				LibraryMemberActionScreen libraryMember = LibraryMemberActionScreen.INSTANCE;
				libraryMember.setStage(ps, roles, selected);
			}
		});
		
		Button newButton = new Button("New");
		newButton.setAlignment(Pos.CENTER);
		newButton.setId("button-new");
		newButton.setPrefWidth(100);
		buttonBox.getChildren().addAll(editButton, newButton);

		newButton.setOnAction(evt -> {
			LibraryMemberActionScreen libraryMember = LibraryMemberActionScreen.INSTANCE;
			libraryMember.setStage(primaryStage, roles, null);
		});

		table = new TableView<Member>();
		table.setPrefSize(1000, 300);
		table.setPadding(new Insets(20, 20, 20, 20));

		TableColumn<Member, Integer> memberIDColumn = new TableColumn<>("MemberID");
		memberIDColumn.setMinWidth(180);
		memberIDColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("memberId"));
		memberIDColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

			@Override
			public Integer fromString(String object) {
				return Integer.parseInt(object);
			}

			@Override
			public String toString(Integer object) {
				return object.toString();
			}
		}));

		TableColumn<Member, String> firstNameColumn = new TableColumn<>("FirstName");
		firstNameColumn.setMinWidth(280);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));
		firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Member, String> lastNameColumn = new TableColumn<>("LastName");
		lastNameColumn.setMinWidth(280);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));
		lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Member, String> phoneNumberColumn = new TableColumn<>("PhoneNumber");
		phoneNumberColumn.setMinWidth(180);
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNumber"));
		phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		table.getColumns().addAll(memberIDColumn, firstNameColumn, lastNameColumn, phoneNumberColumn);

		MenuBar mainMenu = new MenuBar();
		Menu home = new Menu("Home");
		MenuItem back = new MenuItem("Back");
		MenuItem logout = new MenuItem("Logout");
		MenuItem exit = new MenuItem("Exit");
		home.getItems().addAll(back, logout, exit);
		mainMenu.getMenus().addAll(home);

		back.setOnAction(evt -> {
			MainScreen welcome = MainScreen.INSTANCE;
			welcome.setStage(primaryStage, roles);
		});

		exit.setOnAction(evt -> Platform.exit());

		logout.setOnAction(evt -> {
			LoginScreen login = LoginScreen.INSTANCE;
			login.start(primaryStage);
		});

		hBoxTable.getChildren().add(table);

		topContainer.getChildren().addAll(mainMenu, hBox, hBoxTable, buttonBox);
		
		setEventForTableView(editButton);

		primaryStage.setScene(new Scene(topContainer, 1000, 520));
		primaryStage.getScene().getStylesheets().add(getClass().getResource("manageMember.css").toExternalForm());
		primaryStage.show();
	}

	private void setEventForTableView(Button editButton) {
		if (table.getSelectionModel().getSelectedItem() == null) {
			editButton.setDisable(true);
		}
		
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	editButton.setDisable(false);
		    } else {
		    	editButton.setDisable(true);
		    }
		});
	}

	public Member getSelected() {
		return selected;
	}

	public void setSelected(Member selected) {
		this.selected = selected;
	}
	
	
}
