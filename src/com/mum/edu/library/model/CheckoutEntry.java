package com.mum.edu.library.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mum.edu.library.xml.module.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckoutEntry", propOrder = {"dateCheckout","dueDate", "borrowItem"})
@XmlRootElement(name = "CheckoutEntry")
public class CheckoutEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "borrowItem", type=BookCopy.class)
	private BookCopy borrowItem;
	
	@XmlElement(name="dateCheckout")
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	private LocalDate dateCheckout;
	
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlElement(name="dueDate")
	private LocalDate dueDate;

	public CheckoutEntry(){}
	
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
