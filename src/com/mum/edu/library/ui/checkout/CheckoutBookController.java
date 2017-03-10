package com.mum.edu.library.ui.checkout;

import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

import com.mum.edu.library.constant.Constant;
import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.dao.impl.MemberDAOImpl;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.rule.ApplicationException;
import com.mum.edu.library.rule.RuleException;
import com.mum.edu.library.rule.RuleSet;
import com.mum.edu.library.rule.RuleSetFactory;
import com.mum.edu.library.ui.LoginScreen;
import com.mum.edu.library.ui.MainScreen;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class CheckoutBookController {
	
	@FXML
	private GridPane topGrid;
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
	
	
	private Stage getStage(){
		return (Stage)btnSearchBook.getScene().getWindow();
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
	
	public String getMemberIdValue(){
		return txbMemberId.getText();
	}
	
	public Book getSelectedItem(){
		try
		{
			Object book = tableBook.getSelectionModel().getSelectedItem();
			/*Object book = selectedItems.get(0);*/
			
			return (Book)book;
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@FXML
	private void MakeCheckoutRecordButtonAction(ActionEvent event){
		try
		{
			RuleSet ruleSet = RuleSetFactory.getRuleSet(CheckoutBookController.this);
		
			
			ruleSet.applyRule(this);
		
		
			int memberId = Integer.parseInt(txbMemberId.getText());
		
			MemberDAOImpl mDao = new MemberDAOImpl();
			Member requiredMember = mDao.getMember(memberId);
								
			Book book = this.getSelectedItem();
			BookCopy bookCopy = book.getAvailableBookCopy();
		
			boolean resultOfCheckout = CheckoutManager.getInstance().makeCheckoutRecord(requiredMember, bookCopy);
		
			if(resultOfCheckout){
				bookCopy.setAvailability(false);
				book.replaceBookCopy(bookCopy);
			
			BookDAO bookDao = new BookDAOImpl();
			bookDao.editCopy(book);
			}
			
			loadData();
		}
		catch(RuleException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
	@FXML
	private void SearchBookActionButton(ActionEvent event) throws ApplicationException{
		
		tableColumnTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
		//tableColumnTitle.setCellFactory(TextFieldTableCell.forTableColumn());
		
		tableColumnIsbnNumber.setCellValueFactory(new PropertyValueFactory<Book,String>("isbnNumber"));
		//tableColumnIsbnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
		
		//tableColumnAuthor = new TableColumn<Book, String>("author");
		tableColumnAuthor.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(StringUtils.join(param.getValue().getAuthor().toArray(),","));
			}
		});
				
		tableColumnIsAvailable.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getIsAvaliable() ? "Yes" : "No");
			}
			
		});
		
		loadData();
		
	}
	
	private void loadData()  throws ApplicationException{
		
		ObservableList<Book> data =
		        FXCollections.observableArrayList();
		for(Book book: CheckoutManager.getInstance().getSearchBookByISBN(txbISBN.getText())){
			data.add(book);
		}
		
		tableBook.setItems(data);
	}
	
}
