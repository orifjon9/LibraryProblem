package com.mum.edu.library.ui.checkout;

import java.util.Set;

import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.CheckoutEntry;
import com.mum.edu.library.model.CheckoutRecord;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.rule.ApplicationException;

import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class CheckoutOverViewController {
	
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
	private void SearchCheckoutRecordButtonAction(ActionEvent event) {
		applySettings();
		loadData();
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
		    					
		    	tableColumnCity.setCellValueFactory(new PropertyValueFactory<Address,String>("city"));
		    	tableColumnState.setCellValueFactory(new PropertyValueFactory<Address,String>("state"));
		    	tableColumnStreet.setCellValueFactory(new PropertyValueFactory<Address,String>("street"));
		    	tableColumnZip.setCellValueFactory(new PropertyValueFactory<Address,String>("zip"));
		    	
		    	Address address = ((CheckoutRecord)newSelection).getMember().getAddress();
				
				ObservableList<Address> addressTable = FXCollections.observableArrayList();
				addressTable.add(address);
				
				tableAddress.setItems(addressTable);				
		    } 
		});
	
	}
	
	private void loadData(){
		ObservableList<CheckoutRecord> data = FXCollections.observableArrayList();
		
		for(CheckoutRecord checkoutRecord: CheckoutManager.getInstance().getAllCheckoutRecord()){
			data.add(checkoutRecord);
		}
		
		tableCheckoutRecord.setItems(data);
	}
	
}
