package com.mum.edu.library.ui.checkout;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.rule.ApplicationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckoutBookController {
	
	@FXML
	private TextField txbMemberId;
	@FXML
	private TextField txbISBN;
	@FXML
	private Button btnMakeCheckout;
	
	@FXML
	private Button btnSearchBook;
	
	@FXML 
	private TableView tableBook;
	@FXML 
	private TableColumn tableColumnTitle;
	
	@FXML 
	private TableColumn tableColumnIsbnNumber;
	
	@FXML 
	private TableColumn tableColumnAuthor;
	
	@FXML 
	private TableColumn tableColumnIsAvailable;
	
	
	@FXML
	private void MakeCheckoutRecordButtonAction(ActionEvent event){
		
		String obj = txbMemberId.getText();
		int memberId = Integer.parseInt(txbMemberId.getText());
		
		boolean resultOfCheckout = CheckoutManager.getInstance().makeCheckoutRecord(memberId, txbISBN.getText());
	}
	
	@FXML
	private void SearchBookActionButton(ActionEvent event) throws ApplicationException{
		
		tableColumnTitle.setCellFactory(new PropertyValueFactory<Book,String>("title"));
		tableColumnIsbnNumber.setCellFactory(new PropertyValueFactory<Book,String>("isbnNumber"));
		tableColumnAuthor.setCellFactory(new PropertyValueFactory<Book,String>("author"));
		tableColumnIsAvailable.setCellFactory(new PropertyValueFactory<Book, String>("availability"));
		
		ObservableList<Book> data =
		        FXCollections.observableArrayList();
		for(Book book: CheckoutManager.getInstance().getSearchBookByISBN(txbISBN.getText())){
			data.add(book);
		}
		
		tableBook.setItems(data);
		
		//boolean resultOfCheckout = CheckoutManager.getInstance().makeCheckoutRecord(memberId, txbISBN.getText());
	}
	
}
