package com.mum.edu.library.ui;

import java.util.Set;

import com.mum.edu.library.api.CommonAPI;
import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Author;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.Role;
import com.mum.edu.library.rule.ApplicationException;
import com.mum.edu.library.rule.RuleException;
import com.mum.edu.library.rule.RuleSet;
import com.mum.edu.library.rule.RuleSetFactory;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AddBookScreen extends Stage {
	public static final AddBookScreen INSTANCE = new AddBookScreen();
	Stage primaryStage;
	boolean finishInput = false;
	private TextField isbn;
	private TextField bookTitle;
	private TextField borrowAbleDate;
	private TextField numberOfCopy;
	private TextField firstName;
	private TextField lastName;
	private TextField phone;
	private TextField credentials;
	private TextField street;
	private TextField city;
	private TextField zip;
	private TextArea shortBio;
	BookDAO bookDAO = new BookDAOImpl();
	
	private ComboBox<String> stateCb;
	
	private AddBookScreen() {
	}
	

	public void setStage(Stage ps, Set<Role> roles) {
		primaryStage = ps;
		primaryStage.setTitle("Book Management - Add new book");

		VBox topContainer = new VBox();

		HBox hbResult = new HBox();
		hbResult.setPadding(new Insets(20, 20, 10, 40));
		hbResult.setAlignment(Pos.TOP_LEFT);
		Label result = new Label();
		result.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
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
		isbnlbl.getStyleClass().add("book-label");
		bookGrid.add(isbnlbl, 0, 1);

		isbn = new TextField();
		isbn.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		isbn.setPrefWidth(220);
		bookGrid.add(isbn, 1, 1);

		Label titleLbl = new Label("Title");
		titleLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		titleLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		titleLbl.getStyleClass().add("book-label");
		bookGrid.add(titleLbl, 0, 2);

		bookTitle = new TextField();
		isbn.setPrefWidth(220);
		bookGrid.add(bookTitle, 1, 2);

		Label borrowAbleDateLbl = new Label("Max Checkout");
		borrowAbleDateLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		borrowAbleDateLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		borrowAbleDateLbl.getStyleClass().add("book-label");
		bookGrid.add(borrowAbleDateLbl, 0, 3);

		borrowAbleDate = new TextField();
		borrowAbleDate.setPrefWidth(220);
		bookGrid.add(borrowAbleDate, 1, 3);

		Label numberOfCopyLbl = new Label("Number of Copy");
		numberOfCopyLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		numberOfCopyLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		numberOfCopyLbl.getStyleClass().add("book-label");
		bookGrid.add(numberOfCopyLbl, 0, 4);

		numberOfCopy = new TextField();
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
		firstNameLbl.getStyleClass().add("book-label");
		authorGrid.add(firstNameLbl, 0, 1);

		firstName = new TextField();
		firstName.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		firstName.setPrefWidth(220);
		authorGrid.add(firstName, 1, 1);

		Label lastNameLbl = new Label("Last Name");
		lastNameLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		lastNameLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		lastNameLbl.getStyleClass().add("book-label");
		authorGrid.add(lastNameLbl, 0, 2);

		lastName = new TextField();
		lastName.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		lastName.setPrefWidth(220);
		authorGrid.add(lastName, 1, 2);

		Label phoneLbl = new Label("Phone");
		phoneLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		phoneLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		phoneLbl.getStyleClass().add("book-label");
		authorGrid.add(phoneLbl, 0, 3);

		phone = new TextField();
		phone.setPrefWidth(220);
		authorGrid.add(phone, 1, 3);

		Label credentialsLbl = new Label("Credentials");
		credentialsLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		credentialsLbl.getStyleClass().add("book-label");
		credentialsLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid.add(credentialsLbl, 0, 4);

		credentials = new TextField();
		credentials.setMaxWidth(220);
		authorGrid.add(credentials, 1, 4);

		Label streetLbl = new Label("Street");
		streetLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		streetLbl.getStyleClass().add("book-label");
		streetLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		authorGrid.add(streetLbl, 0, 5);

		street = new TextField();
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
		cityLbl.getStyleClass().add("book-label");
		authorGrid2.add(cityLbl, 0, 1);

		city = new TextField();
		city.setMaxWidth(220);
		authorGrid2.add(city, 1, 1);

		Label stateLbl = new Label("State");
		stateLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		stateLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		stateLbl.getStyleClass().add("book-label");
		authorGrid2.add(stateLbl, 0, 2);

		ObservableList<String> options = FXCollections.observableArrayList(CommonAPI.getUSState());
		stateCb = new ComboBox<>(options);
		stateCb.setMaxWidth(220);
		authorGrid2.add(stateCb, 1, 2);

		Label zipLbl = new Label("Zip");
		zipLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		zipLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		zipLbl.getStyleClass().add("book-label");
		authorGrid2.add(zipLbl, 0, 3);

		zip = new TextField();
		zip.setPrefWidth(220);
		authorGrid2.add(zip, 1, 3);

		Label shortBioLbl = new Label("Short Bio");
		shortBioLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		shortBioLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		shortBioLbl.getStyleClass().add("book-label");
		authorGrid2.add(shortBioLbl, 0, 4);

		shortBio = new TextArea();
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
			BookManagementScreen bookManagementScreen = BookManagementScreen.INSTANCE;
			bookManagementScreen.setStage(primaryStage, roles);
			
			ObservableList<Book> books = null;
			try {
				books = FXCollections.observableArrayList(bookDAO.read());
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			bookManagementScreen.setData(books);
			bookManagementScreen.show();
			hide();
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
		btnSubmit.getStyleClass().add("button-addBook");
		btnSubmit.setPrefWidth(100);
		hbBtn.getChildren().addAll(btnSubmit);

		topContainer.getChildren().addAll(mainMenu, hBox, hbResult, mainContentHBox, hbBtn);

		btnSubmit.setOnAction(evt -> {
			RuleSet ruleSet = RuleSetFactory.getRuleSet(AddBookScreen.this);
			try {
				ruleSet.applyRule(AddBookScreen.this);
			} catch (RuleException e) {
				result.setText(e.getMessage());
				return;
			}
			
			Book book = new Book(bookTitle.getText(), isbn.getText());
			book.getAuthor()
					.add(new Author(firstName.getText(), lastName.getText(),
							new Address(street.getText(), city.getText(), stateCb.getValue(), zip.getText()),
							phone.getText(), credentials.getText(), shortBio.getText()));
			for (Integer i = 1; i <= Integer.parseInt(numberOfCopy.getText()); i++) {
				book.getBookCopies().add(new BookCopy(i, Integer.parseInt(borrowAbleDate.getText()), true));
			}

			try {
				bookDAO.save(book);
			} catch (ApplicationException e) {
				showErrorDialog(e.getMessage());
				return;
			}
			String message = "Create New Book Successfuly, ISBN is : " +book.getIsbnNumber();
			showInformationDialog(message);
			clearAllData();
		});

		Scene newScene = new Scene(topContainer, 1075, 520);
		newScene.getStylesheets().add(getClass().getResource("manageMember.css").toExternalForm());
		setScene(newScene);
	}
	
	private void clearAllData() {
		isbn.clear();
		bookTitle.clear();
		borrowAbleDate.clear();
		numberOfCopy.clear();
		firstName.clear();
		lastName.clear();
		phone.clear();
		credentials.clear();
		street.clear();
		city.clear();
		stateCb.setValue("");;
		zip.clear();
		shortBio.clear();
	}


	private void showErrorDialog(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Look, an Error Dialog");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	private void showInformationDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}


	public TextField getIsbn() {
		return isbn;
	}

	public void setIsbn(TextField isbn) {
		this.isbn = isbn;
	}

	public TextField getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(TextField bookTitle) {
		this.bookTitle = bookTitle;
	}

	public TextField getBorrowAbleDate() {
		return borrowAbleDate;
	}

	public void setBorrowAbleDate(TextField borrowAbleDate) {
		this.borrowAbleDate = borrowAbleDate;
	}

	public TextField getNumberOfCopy() {
		return numberOfCopy;
	}

	public void setNumberOfCopy(TextField numberOfCopy) {
		this.numberOfCopy = numberOfCopy;
	}

	public TextField getFirstName() {
		return firstName;
	}

	public void setFirstName(TextField firstName) {
		this.firstName = firstName;
	}

	public TextField getLastName() {
		return lastName;
	}

	public void setLastName(TextField lastName) {
		this.lastName = lastName;
	}

	public TextField getPhone() {
		return phone;
	}

	public void setPhone(TextField phone) {
		this.phone = phone;
	}

	public TextField getCredentials() {
		return credentials;
	}

	public void setCredentials(TextField credentials) {
		this.credentials = credentials;
	}

	public TextField getStreet() {
		return street;
	}

	public void setStreet(TextField street) {
		this.street = street;
	}

	public TextField getCity() {
		return city;
	}

	public void setCity(TextField city) {
		this.city = city;
	}

	
	public ComboBox<String> getStateCb() {
		return stateCb;
	}

	public void setStateCb(ComboBox<String> stateCb) {
		this.stateCb = stateCb;
	}

	public TextField getZip() {
		return zip;
	}

	public void setZip(TextField zip) {
		this.zip = zip;
	}

	public TextArea getShortBio() {
		return shortBio;
	}

	public void setShortBio(TextArea shortBio) {
		this.shortBio = shortBio;
	}

}
