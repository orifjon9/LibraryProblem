package com.mum.edu.library.ui;

import java.util.Set;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.model.Book;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BookManagementScreen {
	public static final BookManagementScreen INSTANCE = new BookManagementScreen();
	
	
	BookDAO bookDAO;
	private TableView<Book> table;
	Stage primaryStage;
	private Book selected;

	public void setData(ObservableList<Book> books) {
		table.setItems(books);
	}

	private BookManagementScreen() {
	}

	@SuppressWarnings("unchecked")
	public void setStage(Stage ps, Set<Role> roles) {
		primaryStage = ps;
		primaryStage.setTitle("Book Management");
		
		bookDAO = new BookDAOImpl();
		VBox topContainer = new VBox();
		topContainer.setSpacing(5);

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(20, 20, 20, 10));
		hBox.setAlignment(Pos.CENTER);
		Label label = new Label("All Book of Library");
		label.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		label.setTextFill(javafx.scene.paint.Color.WHITE);
		hBox.getChildren().add(label);
		
		HBox hBoxTable = new HBox();
		hBoxTable.setPadding(new Insets(0, 20, 0, 20));

		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
		buttonBox.setPadding(new Insets(10, 20, 0, 0));

		Button btnAdd = new Button("Add Copy");
		btnAdd.setAlignment(Pos.CENTER);
		btnAdd.setId("button-add");
		btnAdd.setPrefWidth(100);
		
		Button btnSearch = new Button("Search");
		btnSearch.setAlignment(Pos.CENTER);
		btnSearch.setId("button-search");
		btnSearch.setPrefWidth(100);
		
		buttonBox.getChildren().addAll(btnSearch, btnAdd);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 10, 0, 10));
		
		// ---------------ISBN Input------------
		
		HBox hBoxSearch = new HBox();
		hBoxSearch.setPadding(new Insets(20, 20, 20, 10));
		hBoxSearch.setAlignment(Pos.TOP_LEFT);
		Label isbnlbl = new Label("ISBN input:");
		isbnlbl.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
		isbnlbl.setTextFill(javafx.scene.paint.Color.WHITE);
		hBoxSearch.getChildren().add(isbnlbl);

//		TextField isbn = new TextField();
//		isbn.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
//		//isbn.setTextFill(javafx.scene.paint.Color.WHITE);
//		grid.add(isbn, 2, 1);
//
//		// ---------------Book's title ------------
//		Label bookTitlelbl = new Label("Book's title:");
//		grid.add(bookTitlelbl, 1, 2);
//
//		TextField bookTitle = new TextField();
//		grid.add(bookTitle, 2, 2);
//		
//		// ---------------Book's number of Copy ------------
//		Label currentNumlbl = new Label("Current number:");
//		grid.add(currentNumlbl, 1, 3);
//
//		TextField currentNum = new TextField();
//		grid.add(currentNum, 2, 3);
//		
		
		btnAdd.setOnAction(evt -> {
			selected = table.getSelectionModel().getSelectedItem();
			if (selected != null) {
				// TODO
				
				
			}
		});
		


		btnSearch.setOnAction(evt -> {
			// TODO
			
			
		});

		table = new TableView<Book>();
		table.setPrefSize(600, 300);
		table.setPadding(new Insets(20, 20, 20, 20));

		TableColumn<Book, String> bookIDColumn = new TableColumn<>("ISBN");
		bookIDColumn.setMinWidth(120);
		bookIDColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbnNumber"));
		bookIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Book, String> bookTitleColumn = new TableColumn<>("Title of Book");
		bookTitleColumn.setMinWidth(440);
		bookTitleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		bookTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());


		table.getColumns().addAll(bookIDColumn, bookTitleColumn);

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

		topContainer.getChildren().addAll(mainMenu, hBox, hBoxSearch, hBoxTable, buttonBox);
		
		setEventForTableView(btnAdd);

		Scene newScene = new Scene(topContainer, 1000, 520);
		primaryStage.setScene(newScene);
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

	public Book getSelected() {
		return selected;
	}

	public void setSelected(Book selected) {
		this.selected = selected;
	}
	
	
}
