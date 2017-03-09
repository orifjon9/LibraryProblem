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
import javafx.scene.control.TextField;
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

	@SuppressWarnings("unchecked")
	public void setStage(Stage ps, Set<Role> roles) {
		primaryStage = ps;
		primaryStage.setTitle("Book Management - Add new book");
		
		//BookDAOImpl bookDAO = new BookDAOImpl();

		VBox topContainer = new VBox();
		topContainer.setSpacing(5);

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 20, 10, 10));
		hBox.setAlignment(Pos.CENTER);
		Label label = new Label("Add new book for Library");
		label.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		label.setTextFill(javafx.scene.paint.Color.WHITE);
		hBox.getChildren().add(label);
		
		// ---------------Result Inform------------
		HBox hbResult = new HBox();
		hbResult.setPadding(new Insets(20, 20, 10, 20));
		hbResult.setAlignment(Pos.TOP_LEFT);
		Label result = new Label("");
		result.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
		result.setTextFill(javafx.scene.paint.Color.RED);
		hbResult.getChildren().add(result);
		
		// ---------------Book Infor Input------------
		HBox hbBook = new HBox();
		hbBook.setPadding(new Insets(20, 20, 0, 20));
		hbBook.setAlignment(Pos.TOP_LEFT);
		Label bookLbl = new Label("Book");
		bookLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		bookLbl.setTextFill(javafx.scene.paint.Color.DEEPSKYBLUE);		
		hbBook.getChildren().add(bookLbl);
		
		HBox hbBookInfo = new HBox();
		hbBookInfo.setPadding(new Insets(10, 20, 20, 20));
		hbBookInfo.setAlignment(Pos.TOP_LEFT);
		
		Label isbnlbl = new Label("ISBN");
		isbnlbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		isbnlbl.setTextFill(javafx.scene.paint.Color.WHITE);
		
		TextField isbn = new TextField("");
		isbn.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		isbn.setMaxWidth(80);

		Label titleLbl = new Label("Title");
		titleLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		titleLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		
		TextField title = new TextField("");
		title.setMinWidth(330);
		
		Label borrowAbleDateLbl = new Label("Max Checkout");
		borrowAbleDateLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		borrowAbleDateLbl.setTextFill(javafx.scene.paint.Color.WHITE);		
		
		TextField borrowAbleDate = new TextField("");
		borrowAbleDate.setMaxWidth(65);
		
		Label numberOfCopyLbl = new Label("Number of Copy");
		numberOfCopyLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		numberOfCopyLbl.setTextFill(javafx.scene.paint.Color.WHITE);	
		
		TextField numberOfCopy = new TextField("");
		numberOfCopy.setMaxWidth(85);
		hbBookInfo.setSpacing(20);
		
		hbBookInfo.getChildren().addAll(isbnlbl, isbn, titleLbl, title, borrowAbleDateLbl, borrowAbleDate, numberOfCopyLbl, numberOfCopy);
		
		// --------------- Author Input------------
		HBox hbAuthor1 = new HBox();
		hbAuthor1.setPadding(new Insets(0, 20, 0, 20));
		hbAuthor1.setAlignment(Pos.TOP_LEFT);
		Label author1 = new Label("Author");
		author1.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		author1.setTextFill(javafx.scene.paint.Color.DEEPSKYBLUE);		
		hbAuthor1.getChildren().add(author1);
		
		// --------------- Author Infor Input------------		
		HBox hbAuthorInfo1a = new HBox();
		hbAuthorInfo1a.setPadding(new Insets(10, 20, 10, 20));
		hbAuthorInfo1a.setSpacing(20);
		hbAuthorInfo1a.setAlignment(Pos.TOP_LEFT);
		
		Label firstNameLbl = new Label("First Name");
		firstNameLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		firstNameLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		
		TextField firstName = new TextField("");
		firstName.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		firstName.setMaxWidth(80);

		Label lastNameLbl = new Label("Last Name");
		lastNameLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		lastNameLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		
		TextField lastName = new TextField("");
		lastName.setMaxWidth(80);
		
		Label phoneLbl = new Label("Phone number");
		phoneLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		phoneLbl.setTextFill(javafx.scene.paint.Color.WHITE);		
		
		TextField phone = new TextField("");
		phone.setMaxWidth(200);
		
		Label credentialsLbl = new Label("CredentialsLbl No");
		credentialsLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		credentialsLbl.setTextFill(javafx.scene.paint.Color.WHITE);	
		
		TextField credentials = new TextField("");
		credentials.setMaxWidth(280);
		
		HBox hbAuthorInfo1b = new HBox();
		hbAuthorInfo1b.setPadding(new Insets(10, 20, 10, 20));
		hbAuthorInfo1b.setAlignment(Pos.TOP_LEFT);
		hbAuthorInfo1b.setSpacing(20);
		
		Label streetLbl = new Label("Street");
		streetLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		streetLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		
		TextField street = new TextField("");
		street.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		street.setMinWidth(250);

		Label cityLbl = new Label("City");
		cityLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		cityLbl.setTextFill(javafx.scene.paint.Color.WHITE);
		
		TextField city = new TextField("");
		city.setMaxWidth(150);
		
		Label stateLbl = new Label("State");
		stateLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		stateLbl.setTextFill(javafx.scene.paint.Color.WHITE);		
		
		TextField state = new TextField("");
		state.setMaxWidth(200);
		
		Label zipLbl = new Label("Zip");
		zipLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		zipLbl.setTextFill(javafx.scene.paint.Color.WHITE);	
		
		TextField zip = new TextField("");
		zip.setMinWidth(125);
		
		HBox hbAuthorInfo1c = new HBox();
		hbAuthorInfo1c.setPadding(new Insets(10, 20, 10, 20));
		hbAuthorInfo1c.setAlignment(Pos.TOP_LEFT);
		hbAuthorInfo1c.setSpacing(20);
		
		Label shortBioLbl = new Label("Short Bio");
		shortBioLbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
		shortBioLbl.setTextFill(javafx.scene.paint.Color.WHITE);	
		
		TextField shortBio = new TextField("");
		shortBio.setMinWidth(880);
		
		hbAuthorInfo1a.getChildren().addAll(firstNameLbl, firstName, lastNameLbl, lastName, phoneLbl, phone, credentialsLbl, credentials);
		hbAuthorInfo1b.getChildren().addAll(streetLbl, street, cityLbl, city, stateLbl, state, zipLbl, zip);
		hbAuthorInfo1c.getChildren().addAll(shortBioLbl, shortBio);

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
		hbBtn.setPadding(new Insets(20, 20, 20, 20));
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		Button btnAddAuthor = new Button("Add Author");
		btnAddAuthor.setId("button-add");
		btnAddAuthor.setPrefWidth(100);
		hbBtn.setSpacing(20);
		Button btnSubmit = new Button("Submit");
		btnSubmit.setId("button-add");
		btnSubmit.setPrefWidth(100);
		hbBtn.getChildren().addAll(btnAddAuthor, btnSubmit);

		topContainer.getChildren().addAll(mainMenu, hBox, hbResult, hbBook, hbBookInfo, hbAuthor1, hbAuthorInfo1a, hbAuthorInfo1b, hbAuthorInfo1c, hbBtn);
		
		btnSubmit.setOnAction(evt -> {
			if(title.getText().length()==0 || isbn.getText().length() == 0)
				result.setText("Please input all fields");
			else
			{			
				BookDAO bookDAO = new BookDAOImpl();
				Book book = new Book(title.getText(), isbn.getText());
				book.getAuthor().add(new Author(firstName.getText(), lastName.getText(),new Address(street.getText(), city.getText(), state.getText(), zip.getText()), 
						phone.getText(),credentials.getText(),shortBio.getText()));
				for(Integer i = 1; i <= Integer.parseInt(numberOfCopy.getText()); i++)
				{
					book.getBookCopies().add(new BookCopy(i, Integer.parseInt(borrowAbleDate.getText()),true));				
				}
	
				try {
					bookDAO.save(book);
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				}
		});		

		btnAddAuthor.setOnAction(evt -> {
			return;
		});	
		
		Scene newScene = new Scene(topContainer, 1000, 520);
		primaryStage.setScene(newScene);
		primaryStage.getScene().getStylesheets().add(getClass().getResource("manageMember.css").toExternalForm());
		primaryStage.show();
	}
	}


