package com.mum.edu.library.ui;

import java.util.Set;

import com.mum.edu.library.model.Member;
import com.mum.edu.library.model.Role;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageMemberScreen {
	public static final ManageMemberScreen INSTANCE = new ManageMemberScreen();
	
	private TableView<Member> table;
	Stage primaryStage;
	
	public void setData(ObservableList<Member> members) {
		table.setItems(members);
	}
	
	private ManageMemberScreen() {
	}

	@SuppressWarnings("unchecked")
	public void setStage(Stage ps, Set<Role> roles) {
		primaryStage = ps;
		primaryStage.setTitle("Member Management");
		
		VBox topContainer = new VBox();
		topContainer.setSpacing(5);
		topContainer.setPadding(new Insets(10, 10, 10, 10));
		Label label = new Label("List Member");
		
		table = new TableView<Member>();
		
		
		TableColumn<Member, String> firstNameColumn = new TableColumn<>("FirstName");
		firstNameColumn.setMinWidth(250);
		firstNameColumn.setCellValueFactory(
	            new PropertyValueFactory<Member, String>("firstName"));
		firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<Member, String> lastNameColumn = new TableColumn<>("LastName");
		lastNameColumn.setMinWidth(250);
		lastNameColumn.setCellValueFactory(
	            new PropertyValueFactory<Member, String>("lastName"));
		lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		table.getColumns().addAll(firstNameColumn,lastNameColumn);
		
		
		topContainer.getChildren().addAll(label,table);
		
		primaryStage.setScene(new Scene(topContainer, 1000, 520));
		primaryStage.show();
	}
	
	
}
