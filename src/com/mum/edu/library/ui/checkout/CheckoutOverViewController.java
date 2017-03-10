package com.mum.edu.library.ui.checkout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import com.mum.edu.library.constant.Constant;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.CheckoutEntry;
import com.mum.edu.library.model.CheckoutRecord;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.rule.ApplicationException;
import com.mum.edu.library.rule.RuleException;
import com.mum.edu.library.rule.RuleSet;
import com.mum.edu.library.rule.RuleSetFactory;
import com.mum.edu.library.ui.LoginScreen;
import com.mum.edu.library.ui.MainScreen;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CheckoutOverViewController {
	
	@FXML
	private VBox topPanel;
	@FXML
	private GridPane topGrid;
	@FXML 
	private MenuItem menuBack;
	@FXML 
	private MenuItem menuLogout;
	@FXML 
	private MenuItem menuExit;
	
	@FXML
	private TableView tableCheckoutRecord;
	
	@FXML 
	private TableColumn tableColumnMemberId;
	@FXML 
	private TableColumn tableColumnMemberName;
	@FXML 
	private TableColumn tableColumnPhone;
	@FXML 
	private TableColumn tableColumnAddress;
	@FXML 
	private TableColumn tableColumnCheckoutDate;
	@FXML 
	private TableColumn tableColumnDueDate;
	
	
	@FXML
	private TableView tableAddress;
	@FXML 
	private TableColumn tableColumnCity;
	@FXML 
	private TableColumn tableColumnState;
	@FXML 
	private TableColumn tableColumnStreet;
	@FXML 
	private TableColumn tableColumnZip;
		
	
	@FXML
	private TableView tableCheckoutBookCopy;
	@FXML 
	private TableColumn tableColumnISBN;
	@FXML 
	private TableColumn tableColumnCopyNumber;
	
	@FXML
	private Button btnSearch;
	@FXML
	private TextField txbMemberId;
	
	@FXML
	private void SearchCheckoutRecordButtonAction(ActionEvent event) {
		applySettings();
		loadData();
	}
	
	@FXML
	private void PrintCheckoutRecordButtonAction(ActionEvent event) {
		
	}
	
	private Stage getStage(){
		return (Stage)btnSearch.getScene().getWindow();
	}
	
	private void showWindow(Stage primaryStage) {
		Scene newScene = new Scene(topGrid, 1000, 540);
		primaryStage.setScene(newScene);
		primaryStage.getScene().getStylesheets().add(getClass().getResource(Constant.RESOURCE_MEMBER_CSS).toExternalForm());
		primaryStage.show();
	}
	
	@FXML
	private void MenuBackButtonAction(ActionEvent event){
		try
		{
			Stage primaryStage = getStage();
		
			MainScreen welcome = MainScreen.INSTANCE;
			welcome.setStage(primaryStage, RoleFactory.getInstance().getRoles());
	   
			showWindow(primaryStage);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@FXML
	private void MenuLogoutButtonAction(ActionEvent event){
		try
		{
			Stage primaryStage = getStage();
		
			LoginScreen login = LoginScreen.INSTANCE;
			login.start(primaryStage);
		
			showWindow(primaryStage);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@FXML
	private void MenuExitButtonAction(ActionEvent event){
		Platform.exit();
	}
	
	private void applySettings(){
		tableColumnMemberId.setCellValueFactory(new Callback<CellDataFeatures<CheckoutRecord, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<CheckoutRecord, String> param) {
				// TODO Auto-generated method stub
				//CheckoutEntry currectCheckoutEntry = param.getValue().getCheckoutEntries().get(0);
				Member member = param.getValue().getMember();
				
				return new SimpleStringProperty(""+member.getMemberId());
			}
			
		});
		
		tableColumnMemberName.setCellValueFactory(new Callback<CellDataFeatures<CheckoutRecord, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<CheckoutRecord, String> param) {
				// TODO Auto-generated method stub
				//CheckoutEntry currectCheckoutEntry = param.getValue().getCheckoutEntries().get(0);
				Member member = param.getValue().getMember();
				
				return new SimpleStringProperty(member.getFirstName() + " " + member.getLastName());
			}
			
		});
		
		tableColumnPhone.setCellValueFactory(new Callback<CellDataFeatures<CheckoutRecord, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<CheckoutRecord, String> param) {
				// TODO Auto-generated method stub
				//CheckoutEntry currectCheckoutEntry = param.getValue().getCheckoutEntries().get(0);
				Member member = param.getValue().getMember();
				
				return new SimpleStringProperty(member.getPhoneNumber());
			}
			
		});
		
		tableColumnCheckoutDate.setCellValueFactory(new Callback<CellDataFeatures<CheckoutRecord, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<CheckoutRecord, String> param) {
				// TODO Auto-generated method stub
				CheckoutEntry currectCheckoutEntry = param.getValue().getCheckoutEntries().get(0);
				
				return new SimpleStringProperty(currectCheckoutEntry.getDateCheckout().toString());
			}
			
		});
		
		tableColumnDueDate.setCellValueFactory(new Callback<CellDataFeatures<CheckoutRecord, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<CheckoutRecord, String> param) {
				// TODO Auto-generated method stub
				CheckoutEntry currectCheckoutEntry = param.getValue().getCheckoutEntries().get(0);
				
				return new SimpleStringProperty(currectCheckoutEntry.getDueDate().toString());
			}
			
		});
	
		tableCheckoutRecord.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    				/*	
		    	tableColumnCity.setCellValueFactory(new PropertyValueFactory<Address,String>("city"));
		    	tableColumnState.setCellValueFactory(new PropertyValueFactory<Address,String>("state"));
		    	tableColumnStreet.setCellValueFactory(new PropertyValueFactory<Address,String>("street"));
		    	tableColumnZip.setCellValueFactory(new PropertyValueFactory<Address,String>("zip"));
		    	*/
		    	
		    	tableColumnISBN.setCellValueFactory(new PropertyValueFactory<Book,String>("isbnNumber"));
		    	tableColumnCopyNumber.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
						// TODO Auto-generated method stub
						BookCopy bookCopy = param.getValue().getBookCopies().iterator().next();
						
						return new SimpleStringProperty("" + bookCopy.getIdCopyNumber());
					}
					
				});
		    	/*
		    	Address address = ((CheckoutRecord)newSelection).getMember().getAddress();
				
				ObservableList<Address> addressTable = FXCollections.observableArrayList();
				addressTable.add(address);
				tableAddress.setItems(addressTable);*/
				
				try
				{
				
				List<Book> books = new BookDAOImpl().read();
				ObservableList<Book> bookTable = FXCollections.observableArrayList();
				
				for(CheckoutEntry checkoutEntry :  ((CheckoutRecord)newSelection).getCheckoutEntries()){
					BookCopy bookCopy = checkoutEntry.getBorrowItem();
					
					for(Book book: books){
						for(BookCopy tmpBookCopy: book.getBookCopies()){
							if(tmpBookCopy.getIdCopyNumber() == bookCopy.getIdCopyNumber()){
								Book newBook = new Book();
								newBook.setIsbnNumber(book.getIsbnNumber());
							
								Set<BookCopy> bookCopies = new HashSet<BookCopy>();
								bookCopies.add(bookCopy);
							
								newBook.setBookCopies(bookCopies);
								bookTable.add(newBook);
							}
						}
					}
					
					//bookTable.add(address);
					tableCheckoutBookCopy.setItems(bookTable);
				}
				}
				catch(ApplicationException ex){
					System.out.print(ex.getMessage());
				}
		    } 
		});
	
	}
	
	private void loadData(){
		
		RuleSet ruleSet = RuleSetFactory.getRuleSet(CheckoutOverViewController.this);
		try {
			ruleSet.applyRule(this);
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}
		
		ObservableList<CheckoutRecord> data = FXCollections.observableArrayList();
		
		for(CheckoutRecord checkoutRecord: CheckoutManager.getInstance().getAllCheckoutRecord()){
			int selectedMemberId = getMemberId();
			if(selectedMemberId == 0){
				data.add(checkoutRecord);
			}
			else
			{
				Member member = checkoutRecord.getMember();
				if(member.getMemberId() == selectedMemberId){
					data.add(checkoutRecord);
				}
			}
		}
		
		tableCheckoutRecord.setItems(data);
	}
	
	public String getMemberIdValue(){
		return txbMemberId.getText();
	}
	
	private int getMemberId(){
		try{
			
			return	Integer.parseInt(getMemberIdValue());
		}
		catch(Exception ex){
			return 0;
		}
	}
	
}
