package com.mum.edu.library.ui;

import java.util.Set;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.rule.ApplicationException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddCopy extends Stage {
	public static final AddCopy INSTANCE = new AddCopy();
	Stage primaryStage;
	
	Integer maxCurrent = 0;
	int borrowAbleDate = 0;
	
	private AddCopy() {
	}

	public void setStage(Stage ps) {
		primaryStage = ps;
		primaryStage.setTitle("Add Copy Of Book");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 10, 0, 10));

		// ---------------ISBN Input------------
		Label isbnlbl = new Label("ISBN:");
		grid.add(isbnlbl, 1, 1);

		TextField isbn = new TextField();
		grid.add(isbn, 2, 1);

		// ---------------Book's title ------------
		Label bookTitlelbl = new Label("Book's title:");
		grid.add(bookTitlelbl, 1, 2);

		TextField bookTitle = new TextField();
		grid.add(bookTitle, 2, 2);
		
		// ---------------Book's number of Copy ------------
		Label currentNumlbl = new Label("Current number:");
		grid.add(currentNumlbl, 1, 3);

		TextField currentNum = new TextField();
		grid.add(currentNum, 2, 3);

		Button btnSearch = new Button("Search");
		HBox hbBtnSeach = new HBox(10);
		hbBtnSeach.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtnSeach.getChildren().add(btnSearch);
		grid.add(btnSearch, 1, 9);
		
		Button btnAdd = new Button("Add Copy");
		HBox hbBtnAdd = new HBox(10);
		hbBtnAdd.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtnAdd.getChildren().add(btnAdd);
		grid.add(hbBtnAdd, 2, 9);
		
		BookDAO bookDAO = new BookDAOImpl();
		
		// Event handle for Search Book by ISBN
		btnSearch.setOnAction(avt -> {
			// To do something
			String inISBN = isbn.getText();
			try {
				Book book = bookDAO.searchBook(inISBN);	

				bookTitle.setText(book.getTitle());
				Set<BookCopy> bookCopies = book.getBookCopies();
			
				for(BookCopy bc:bookCopies)
				{
						if(bc.getIdCopyNumber() > maxCurrent)
						{
							maxCurrent = bc.getIdCopyNumber();
							borrowAbleDate = bc.getBorrowAbleDate();
						}
				}
				currentNum.setText(maxCurrent.toString());
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		});
		
		// Event handle Add Book
		btnAdd.setOnAction(avt -> {
			// To do something
			try {
				Book book = bookDAO.searchBook(isbn.getText());
				Set<BookCopy> bookCopies = book.getBookCopies();
				BookCopy newCopy = new BookCopy(maxCurrent + 1, borrowAbleDate, true);
				bookCopies.add(newCopy);
				book.setBookCopies(bookCopies);
				bookDAO.addCopy(book);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		});
		
		primaryStage.setScene(new Scene(grid, 800, 400));
		primaryStage.show();
	}

}
