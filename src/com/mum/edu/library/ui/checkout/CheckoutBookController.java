package com.mum.edu.library.ui.checkout;

import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;

import com.mum.edu.library.dao.impl.MemberDAOImpl;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.rule.ApplicationException;
import com.mum.edu.library.rule.CheckoutRuleSet;
import com.mum.edu.library.rule.RuleException;
import com.mum.edu.library.rule.RuleSet;
import com.mum.edu.library.rule.RuleSetFactory;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;


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
	
	
	public String getMemberIdValue(){
		return txbMemberId.getText();
	}
	
	public Book getSelectedItem(){
		try
		{
			ObservableList selectedItems = tableBook.getSelectionModel().getSelectedCells();
			return (Book)selectedItems.get(0);
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@FXML
	private void MakeCheckoutRecordButtonAction(ActionEvent event){
		/*try
		{*/
			//RuleSet ruleSet = RuleSetFactory.getRuleSet(CheckoutBookWindow.getInstance());
		
			//ruleSet.applyRule(CheckoutBookWindow.getInstance());
		
		
		
		int memberId = Integer.parseInt(txbMemberId.getText());
		
		Member requiredMember = null; 
		try{
			MemberDAOImpl mDao = new MemberDAOImpl();
			requiredMember = mDao.getMember(memberId);
		}
		catch(ApplicationException ex){
			
		}
		//ObservableList selectedItems = tableBook.getSelectionModel().getSelectedCells();
		Book book = this.getSelectedItem();
		if(book != null && requiredMember != null){
		
			boolean resultOfCheckout = CheckoutManager.getInstance()
					.makeCheckoutRecord(requiredMember, book.getAvailableBookCopy());
		}
		/*}
		catch(RuleException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}*/
	}
	
	@FXML
	private void SearchBookActionButton(ActionEvent event) throws ApplicationException{
		
		tableColumnTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
		tableColumnTitle.setCellFactory(TextFieldTableCell.forTableColumn());
		
		tableColumnIsbnNumber.setCellValueFactory(new PropertyValueFactory<Book,String>("isbnNumber"));
		tableColumnIsbnNumber.setCellFactory(TextFieldTableCell.forTableColumn());
		
		//tableColumnAuthor = new TableColumn<Book, String>("author");
		tableColumnAuthor.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(StringUtils.join(param.getValue().getAuthor().toArray(),","));
			}
		});
				
		tableColumnIsAvailable.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("availability"));
		
		ObservableList<Book> data =
		        FXCollections.observableArrayList();
		for(Book book: CheckoutManager.getInstance().getSearchBookByISBN(txbISBN.getText())){
			data.add(book);
		}
		
		tableBook.setItems(data);
	}
	
}
