package com.mum.edu.library.ui;

import java.util.Set;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.Role;
import com.mum.edu.library.rule.ApplicationException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class BookManagementScreen {
	public static final BookManagementScreen INSTANCE = new BookManagementScreen();
	
	
	BookDAO bookDAO;
	private TableView<Book> table;
	private TableView<BookCopy> tableCopy;
	Stage primaryStage;
	private Book selected;
	ObservableList<Book> books_orin = null;

	public void setData(ObservableList<Book> books) {
		table.setItems(books);
		books_orin = FXCollections.observableArrayList(books);
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

		HBox titleHBox = new HBox();
		titleHBox.setPadding(new Insets(10, 20, 10, 10));
		titleHBox.setAlignment(Pos.CENTER);
		Label label = new Label("All Book Of Library");
		label.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		label.setTextFill(javafx.scene.paint.Color.WHITE);
		titleHBox.getChildren().add(label);
		
		HBox isbnResultHBox = new HBox();
		isbnResultHBox.setPadding(new Insets(10, 10, 0, 20));
		isbnResultHBox.setAlignment(Pos.TOP_LEFT);
		
		HBox bookHBox = new HBox();
		bookHBox.setPadding(new Insets(10, 20, 0, 520));
		bookHBox.setSpacing(265);
		bookHBox.setAlignment(Pos.TOP_LEFT);
		Button btnAddBook = new Button("Add Book");
		btnAddBook.getStyleClass().add("button-addBook");
		btnAddBook.setId("button-addBook");
		btnAddBook.setPrefWidth(100);		
		bookHBox.getChildren().add(btnAddBook);
		
		HBox hBoxTable = new HBox();
		hBoxTable.setPadding(new Insets(0, 20, 0, 20));

		// ---------------ISBN Input------------
		
		HBox hBoxSearch = new HBox();
		hBoxSearch.setPadding(new Insets(10, 20, 20, 20));
		hBoxSearch.setAlignment(Pos.TOP_LEFT);
		Label isbnlbl = new Label("ISBN");
		isbnlbl.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		isbnlbl.setTextFill(javafx.scene.paint.Color.DEEPSKYBLUE);
		
		TextField isbn = new TextField();
		isbn.setMinWidth(180);
		isbn.setFont(Font.font("Arial", FontWeight.NORMAL, 13));

		Label resultSearch = new Label();
		resultSearch.setMinWidth(314);
		resultSearch.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		resultSearch.setStyle("-fx-text-fill: Red");
		isbnResultHBox.getChildren().add(resultSearch);
		
		Button btnSearch = new Button();
		Image image = new Image(getClass().getResourceAsStream("search.png"));
		btnSearch.setGraphic(new ImageView(image));
		btnSearch.setId("button-search");
		btnSearch.setPrefWidth(30);
		btnSearch.setPrefHeight(26);
		
		Button btnViewAll = new Button();
		Image image2 = new Image(getClass().getResourceAsStream("all.png"));
		btnViewAll.setGraphic(new ImageView(image2));
		btnViewAll.setId("button-viewAll");
		btnViewAll.setPrefWidth(26);
		btnViewAll.setPrefHeight(24);
		
		hBoxSearch.setSpacing(10);
		
		Button btnAddCopy = new Button("Add Copy");
		btnAddCopy.getStyleClass().add("button-addBook");
		btnAddCopy.setId("button-add");
		btnAddCopy.setPrefWidth(100);
		bookHBox.getChildren().add(btnAddCopy);
		
		hBoxSearch.getChildren().addAll(isbnlbl, isbn , btnSearch, btnViewAll);

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
		
		hBoxTable.setSpacing(20);

		tableCopy = new TableView<BookCopy>();
		tableCopy.setPrefSize(340, 300);
		tableCopy.setPadding(new Insets(20, 20, 20, 20));

		TableColumn<BookCopy, Integer> copyNumColumn = new TableColumn<>("Copy Number");
		copyNumColumn.setMinWidth(120);
		copyNumColumn.setCellValueFactory(new PropertyValueFactory<BookCopy, Integer>("IdCopyNumber"));
		copyNumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
			@Override
			public Integer fromString(String object) {
				return Integer.parseInt(object);
			}

			@Override
			public String toString(Integer object) {
				return object.toString();
			}
		}));
		TableColumn<BookCopy, Integer> borrowAbleColumn = new TableColumn<>("Borrow Date");
		borrowAbleColumn.setMinWidth(80);
		borrowAbleColumn.setCellValueFactory(new PropertyValueFactory<BookCopy, Integer>("BorrowAbleDate"));
		borrowAbleColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
			@Override
			public Integer fromString(String object) {
				return Integer.parseInt(object);
			}

			@Override
			public String toString(Integer object) {
				return object.toString();
			}
		}));
		
		TableColumn<BookCopy, Boolean> statusColumn = new TableColumn<>("Available ?");
		statusColumn.setMinWidth(100);
		statusColumn.setCellValueFactory(new PropertyValueFactory<BookCopy, Boolean>("availability"));
		statusColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Boolean>() {
			@Override
			public Boolean fromString(String object) {
				if(object.equals("Yes"))
					return true;
				else
					return false;
			}

			@Override
			public String toString(Boolean object) {
				if(object.equals(true))
					return "Yes";
				else
					return "No";
			}
		}));

		tableCopy.getColumns().addAll(copyNumColumn, borrowAbleColumn, statusColumn);
		
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

		hBoxTable.getChildren().addAll(table, tableCopy);

		topContainer.getChildren().addAll(mainMenu, titleHBox, isbnResultHBox, hBoxSearch, hBoxTable, bookHBox);
		
		btnAddCopy.setOnAction(evt -> {
			try {
				selected = table.getSelectionModel().getSelectedItem();
				if (selected != null) {
					Set<BookCopy> bookCopies = selected.getBookCopies();
					Integer maxCurrent = 0;
					Integer borrowAbleDate = 0;
					
					for(BookCopy bc:bookCopies)
					{
							if(bc.getIdCopyNumber() > maxCurrent)
							{
								maxCurrent = bc.getIdCopyNumber();
								borrowAbleDate = bc.getBorrowAbleDate();
							}
					}
					
					BookCopy newCopy = new BookCopy(maxCurrent + 1, borrowAbleDate, true);
					bookCopies.add(newCopy);
					selected.setBookCopies(bookCopies);
					bookDAO.editCopy(selected);
					
					ObservableList<Book> newbooks = null;
					newbooks = FXCollections.observableArrayList(bookDAO.read());
					setData(newbooks);
					table.getSelectionModel().clearSelection();
					resultSearch.setText("Add copy of this book completed");
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		});		

		btnSearch.setOnAction(evt -> {
			String inISBN = isbn.getText();
			try {
				selected = bookDAO.searchBook(inISBN);
				if(selected !=null)
				{
					resultSearch.setText("Search completed");
					ObservableList<Book> newbook =  FXCollections.observableArrayList();
					newbook.add(selected);
	
					table.setItems(newbook);
					// clear the selection
					table.getSelectionModel().clearSelection();	
				}
				else
					resultSearch.setText("This ISBN does not exist");
				
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			
		});
		
		btnViewAll.setOnAction(evt -> {
			resultSearch.setText("");
			try {
				ObservableList<Book> newbooks = null;
				newbooks = FXCollections.observableArrayList(bookDAO.read());
				setData(newbooks);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			table.getSelectionModel().clearSelection();
		});
		
		
		btnAddBook.setOnAction(evt -> {
			AddBookScreen addBook = AddBookScreen.INSTANCE;
			addBook.setStage(primaryStage, roles);		
		});
		
		setEventForTableView(btnAddCopy);

		Scene newScene = new Scene(topContainer, 1000, 540);
		primaryStage.setScene(newScene);
		primaryStage.getScene().getStylesheets().add(getClass().getResource("manageMember.css").toExternalForm());
		primaryStage.show();
	}
	
	private void setEventForTableView(Button btnAdd) {
		if (table.getSelectionModel().getSelectedItem() == null) {
			btnAdd.setDisable(true);
		}
		
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	btnAdd.setDisable(false);
				Set<BookCopy> bookCopiess = null;
				bookCopiess = newSelection.getBookCopies();
				ObservableList<BookCopy> bookcopyToAdd = FXCollections.observableArrayList(bookCopiess);
				tableCopy.setItems(bookcopyToAdd);
		    } else {
		    	btnAdd.setDisable(true);
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
