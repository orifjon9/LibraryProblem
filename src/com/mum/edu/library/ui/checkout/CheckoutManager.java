package com.mum.edu.library.ui.checkout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.mum.edu.library.rule.ApplicationException;

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

	public boolean makeCheckoutRecord(Member selectedMember, BookCopy bookCopy){
		
		//JOptionPane.showMessageDialog(null, memberId + " " + isbn);
		System.out.println(selectedMember.getMemberId());
		
		//Member selectedMember = new Member(1,"test1","test2", new Address(), "test3");
		List<CheckoutEntry> checkoutEntries = new ArrayList<CheckoutEntry>();
		CheckoutEntry checkoutEntry = new  CheckoutEntry(bookCopy);
		
		checkoutEntry.setDateCheckout(LocalDate.now());
		checkoutEntry.setDueDate(LocalDate.now().plusDays(bookCopy.getBorrowAbleDate()));
		
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
	
	public List<Book> getSearchBookByISBN(String isbn) throws ApplicationException{
		try
		{
			BookDAO bookDAO = new BookDAOImpl();
			return bookDAO.searchBooks(isbn);
		}
		catch (ApplicationException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
}
