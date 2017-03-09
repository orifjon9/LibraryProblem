package com.mum.edu.library.ui;

import java.util.Set;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Author;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.Role;
import com.mum.edu.library.rule.ApplicationException;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddBookScreen {
	public static final AddBookScreen INSTANCE = new AddBookScreen();
	Stage primaryStage;
	boolean finishInput = false;

	private AddBookScreen() {
	}

	public void setStage(Stage ps, Set<Role> roles) {
		primaryStage = ps;
		primaryStage.setTitle("Book Management - Add new book");

		VBox topContainer = new VBox();

		HBox hbResult = new HBox();
		hbResult.setPadding(new Insets(0, 20, 10, 10));
		hbResult.setAlignment(Pos.TOP_LEFT);
		Label result = new Label("");
		result.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
		result.setTextFill(javafx.scene.paint.Color.RED);
		hbResult.getChildren().add(result);

		// create Grid View to contain 2 block Book And Author or HBOX
		HBox mainContentHBox = new HBox();
		mainContentHBox.setSpacing(40);
		mainContentHBox.setAlignment(Pos.TOP_LEFT);
		mainContentHBox.setPadding(new Insets(10, 10, 10, 40));

		GridPane bookGrid = new GridPane();
		bookGrid.setHgap(10);
		bookGrid.setVgap(20);

		// ---------------Book Infor Input------------
		HBox hbBook = new HBox();
		// hbBook.setPadding(new Insets(20, 20, 0, 20));
		hbBook.setAlignment(Pos.TOP_LEFT);
		Label bookLbl = new Label("Book");
		bookLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		bookLbl.setTextFill(javafx.scene.paint.Color.DEEPSKYBLUE);
		hbBook.getChildren().add(bookLbl);
		bookGrid.add(hbBook, 0, 0);

		Label isbnlbl = new Label("ISBN");
		isbnlbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		isbnlbl.setTextFill(javafx.scene.paint.Color.WHITE);
		bookGrid.add(isbnlbl, 0, 1);

		TextField isbn = new TextField();
		isbn.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		isbn.setPrefWidth(220);
		bookGrid.add(isbn, 1, 1);

		Label titleLbl = new Label("Title");
		titleLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		titleLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		bookGrid.add(titleLbl, 0, 2);

		TextField title = new TextField();
		isbn.setPrefWidth(220);
		bookGrid.add(title, 1, 2);

		Label borrowAbleDateLbl = new Label("Max Checkout");
		borrowAbleDateLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		borrowAbleDateLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		bookGrid.add(borrowAbleDateLbl, 0, 3);

		TextField borrowAbleDate = new TextField();
		borrowAbleDate.setPrefWidth(220);
		bookGrid.add(borrowAbleDate, 1, 3);

		Label numberOfCopyLbl = new Label("Number of Copy");
		numberOfCopyLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		numberOfCopyLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		bookGrid.add(numberOfCopyLbl, 0, 4);

		TextField numberOfCopy = new TextField();
		numberOfCopy.setPrefWidth(220);
		bookGrid.add(numberOfCopy, 1, 4);

		GridPane authorGrid = new GridPane();
		authorGrid.setHgap(10);
		authorGrid.setVgap(20);

		HBox hbAuthorInfo = new HBox();
		hbAuthorInfo.setPadding(new Insets(0, 20, 0, 0));
		hbAuthorInfo.setAlignment(Pos.TOP_LEFT);
		Label author = new Label("Author");
		author.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		author.setTextFill(javafx.scene.paint.Color.DEEPSKYBLUE);
		hbAuthorInfo.getChildren().add(author);

		authorGrid.add(hbAuthorInfo, 0, 0);

		Label firstNameLbl = new Label("First Name");
		firstNameLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		firstNameLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid.add(firstNameLbl, 0, 1);

		TextField firstName = new TextField();
		firstName.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		firstName.setPrefWidth(220);
		authorGrid.add(firstName, 1, 1);

		Label lastNameLbl = new Label("Last Name");
		lastNameLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		lastNameLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid.add(lastNameLbl, 0, 2);

		TextField lastName = new TextField();
		lastName.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		lastName.setPrefWidth(220);
		authorGrid.add(lastName, 1, 2);

		Label phoneLbl = new Label("Phone");
		phoneLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		phoneLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid.add(phoneLbl, 0, 3);

		TextField phone = new TextField();
		phone.setPrefWidth(220);
		authorGrid.add(phone, 1, 3);

		Label credentialsLbl = new Label("Credentials");
		credentialsLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		credentialsLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid.add(credentialsLbl, 0, 4);

		TextField credentials = new TextField();
		credentials.setMaxWidth(220);
		authorGrid.add(credentials, 1, 4);

		Label streetLbl = new Label("Street");
		streetLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		streetLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid.add(streetLbl, 0, 5);

		TextField street = new TextField();
		street.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		street.setMaxWidth(220);
		authorGrid.add(street, 1, 5);

		GridPane authorGrid2 = new GridPane();
		authorGrid2.setHgap(10);
		authorGrid2.setVgap(20);
		authorGrid2.setPadding(new Insets(15, 0, 0, 0));

		Label cityLbl = new Label("City");
		cityLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		cityLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid2.add(cityLbl, 0, 1);

		TextField city = new TextField();
		city.setMaxWidth(220);
		authorGrid2.add(city, 1, 1);

		Label stateLbl = new Label("State");
		stateLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		stateLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid2.add(stateLbl, 0, 2);

		TextField state = new TextField();
		state.setMaxWidth(220);
		authorGrid2.add(state, 1, 2);

		Label zipLbl = new Label("Zip");
		zipLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		zipLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid2.add(zipLbl, 0, 3);

		TextField zip = new TextField();
		zip.setPrefWidth(220);
		authorGrid2.add(zip, 1, 3);

		Label shortBioLbl = new Label("Short Bio");
		shortBioLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		shortBioLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid2.add(shortBioLbl, 0, 4);

		TextArea shortBio = new TextArea();
		shortBio.setPrefWidth(220);
		shortBio.setPrefHeight(80);
		authorGrid2.add(shortBio, 1, 4);

		mainContentHBox.getChildren().addAll(bookGrid, authorGrid, authorGrid2);

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 20, 10, 10));
		hBox.setAlignment(Pos.CENTER);
		Label label = new Label("Add New Book For Library");
		label.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		label.setTextFill(javafx.scene.paint.Color.WHITE);
		hBox.getChildren().add(label);
		//
		// Main Menu
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

		HBox hbBtn = new HBox();
		hbBtn.setPadding(new Insets(15, 45, 0, 0));
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.setSpacing(20);
		Button btnSubmit = new Button("Submit");
		btnSubmit.setId("button-add");
		btnSubmit.setPrefWidth(100);
		hbBtn.getChildren().addAll(btnSubmit);

		topContainer.getChildren().addAll(mainMenu, hBox, hbResult, mainContentHBox, hbBtn);

		btnSubmit.setOnAction(evt -> {
			if (title.getText().length() == 0 || isbn.getText().length() == 0)
				result.setText("Please input all fields");
			else {
				BookDAO bookDAO = new BookDAOImpl();
				Book book = new Book(title.getText(), isbn.getText());
				book.getAuthor()
						.add(new Author(firstName.getText(), lastName.getText(),
								new Address(street.getText(), city.getText(), state.getText(), zip.getText()),
								phone.getText(), credentials.getText(), shortBio.getText()));
				for (Integer i = 1; i <= Integer.parseInt(numberOfCopy.getText()); i++) {
					book.getBookCopies().add(new BookCopy(i, Integer.parseInt(borrowAbleDate.getText()), true));
				}

				try {
					bookDAO.save(book);
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
			}
		});

		Scene newScene = new Scene(topContainer, 1100, 520);
		primaryStage.setScene(newScene);
		primaryStage.getScene().getStylesheets().add(getClass().getResource("manageMember.css").toExternalForm());
		primaryStage.show();
	}
}
