package com.mum.edu.library.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookCopy implements Serializable {

	private static final long serialVersionUID = 1L;
	private int IdCopyNumber;
	private int BorrowAbleDate;
	private boolean availability;

	public BookCopy() {
	}

	public BookCopy(int idCopyNumber, int borrowAbleDate, boolean availability) {
		IdCopyNumber = idCopyNumber;
		BorrowAbleDate = borrowAbleDate;
		this.availability = availability;
	}

	public int getIdCopyNumber() {
		return IdCopyNumber;
	}

	public int getBorrowAbleDate() {
		return BorrowAbleDate;
	}

	public boolean getAvailability() {
		return availability;
	}

	@XmlAttribute
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@XmlAttribute
	public void setIdCopyNumber(int idCopyNumber) {
		IdCopyNumber = idCopyNumber;
	}

	@XmlAttribute
	public void setBorrowAbleDate(int borrowAbleDate) {
		BorrowAbleDate = borrowAbleDate;
	}

	@Override
	public String toString() {
		return "This is information of Book [IdCopyNumber: " + IdCopyNumber + " ][BorrowAbleDate: " + BorrowAbleDate
				+ " ][availability: " + availability + "]";
	}
}
