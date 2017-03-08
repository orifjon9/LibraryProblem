package com.mum.edu.library.ui.checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.dao.ICheckoutDAO;
import com.mum.edu.library.dao.impl.BookDAOImpl;
import com.mum.edu.library.dao.impl.CheckoutDAOImpl;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.CheckoutEntry;
import com.mum.edu.library.model.CheckoutRecord;
import com.mum.edu.library.model.Member;

public class CheckoutManager {
	private static CheckoutManager instance;
	private List<CheckoutRecord> checkoutRecords;
	private ICheckoutDAO checkoutDAO = new CheckoutDAOImpl();
	
	static{
		instance = new CheckoutManager();		
	}
	
	private CheckoutManager(){}
	
	public static CheckoutManager getInstance(){
		return instance;
	}

	public boolean makeCheckoutRecord(int memberId, String isbn){
		
		//JOptionPane.showMessageDialog(null, memberId + " " + isbn);
		System.out.println(memberId + " " + isbn);
		
		Member selectedMember = new Member(1,"test1","test2", new Address(), "test3");
		List<CheckoutEntry> checkoutEntries = new ArrayList<CheckoutEntry>();
		CheckoutEntry checkoutEntry = new  CheckoutEntry(new BookCopy(123, 0));
		//CheckoutEntry checkoutEntry = new  CheckoutEntry();
		checkoutEntry.setDateCheckout(LocalDate.now());
		checkoutEntry.setDueDate(LocalDate.now().plusDays(7));
		
		checkoutEntries.add(checkoutEntry);
		
		CheckoutRecord checkoutRecord = new CheckoutRecord(selectedMember,  checkoutEntries);
		addCheckoutRecord(checkoutRecord);
		//System.out.println(checkoutRecord);
		
		return true;
	}
	
	public List<CheckoutRecord> getAllCheckoutRecord(){
		if(checkoutRecords == null){
			loadCheckoutRecordsFromDB();
		}
		
		return checkoutRecords;
	}

	private void loadCheckoutRecordsFromDB() {
		checkoutRecords = checkoutDAO.read();
	}
	
	private void addCheckoutRecord(CheckoutRecord checkoutRecord) {
		// TODO Auto-generated method stub
		if(checkoutRecords == null){
			checkoutRecords = new ArrayList<>();
		}
		
		if(checkoutRecords.add(checkoutRecord)){
			checkoutDAO.save(checkoutRecord);
		}
	}
	
	public List<Book> getSearchBookByISBN(String isbn){
		BookDAO bookDAO = new BookDAOImpl();
		return bookDAO.searchBooks(isbn);
	}
	
}
