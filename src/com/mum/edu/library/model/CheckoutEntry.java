package com.mum.edu.library.model;

import java.time.LocalDate;
import java.util.Date;

public class CheckoutEntry {
	private BookCopy borrowItem;
	private LocalDate dateCheckout;
	private LocalDate dueDate;

	public CheckoutEntry(BookCopy aBorrowItem){
		this.borrowItem = aBorrowItem;
	}
	
	public BookCopy getBorrowItem() {
		return borrowItem;
	}

	public void setBorrowItem(BookCopy borrowItem) {
		this.borrowItem = borrowItem;
	}

	public LocalDate getDateCheckout() {
		return dateCheckout;
	}

	public void setDateCheckout(LocalDate dateCheckout) {
		this.dateCheckout = dateCheckout;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

}
