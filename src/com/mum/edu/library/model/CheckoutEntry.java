package com.mum.edu.library.model;

import java.util.Date;

public class CheckoutEntry {
	private BookCopy borrowItem;
	private Date dateCheckout;
	private Date dueDate;

	public BookCopy getBorrowItem() {
		return borrowItem;
	}

	public void setBorrowItem(BookCopy borrowItem) {
		this.borrowItem = borrowItem;
	}

	public Date getDateCheckout() {
		return dateCheckout;
	}

	public void setDateCheckout(Date dateCheckout) {
		this.dateCheckout = dateCheckout;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
