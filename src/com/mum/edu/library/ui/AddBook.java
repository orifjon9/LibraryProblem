package com.mum.edu.library.ui;

import java.util.Set;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.dao.impl.MemberDAOImpl;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Author;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
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

public class AddBook extends Stage {
	public static final AddBook INSTANCE = new AddBook();
	Stage primaryStage;

	private AddBook() {
	}

	public void setStage(Stage ps) {
		primaryStage = ps;
		primaryStage.setTitle("Add Book");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 10, 0, 10));
		
		// ---------------Book's title ------------
		Label bookTitlelbl = new Label("Book's title:");
		grid.add(bookTitlelbl, 1, 1);

		TextField bookTitle = new TextField();
		grid.add(bookTitle, 2, 1);

		// ---------------ISBN ------------
		Label isbnlbl = new Label("ISBN:");
		grid.add(isbnlbl, 1, 2);

		TextField isbn = new TextField();
		grid.add(isbn, 2, 2);
		
		// TO DO: Define for authors and availability
		
	
		Button btnAddBook = new Button("Add Book");
		HBox hbBtnAddBook = new HBox(10);
		hbBtnAddBook.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtnAddBook.getChildren().add(btnAddBook);
		grid.add(hbBtnAddBook, 2, 9);

		
		// Event handle for Search Book by ISBN
		btnAddBook.setOnAction(avt -> {
			// To do something			
			BookDAO bookDAO = new BookDAOImpl();
			Book book = new Book("The Call of the Wild", "12345", true);
			book.getAuthor().add(new Author("Jack","London",new Address("407 SD st", "Fairfield", "Iowa", "52556"), 
					"123456","Certificate","Good"));
			bookDAO.save(book);	
		
		});
		
	
		primaryStage.setScene(new Scene(grid, 800, 400));
		primaryStage.show();
	}

}