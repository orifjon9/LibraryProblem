package com.mum.edu.library.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookCopy implements Serializable {

	private static final long serialVersionUID = 1L;
	private int IdCopyNumber;
	private int BorrowAbleDate;

	public BookCopy() {
	}
	
	public BookCopy(int idCopyNumber, int borrowAbleDate) {
		IdCopyNumber = idCopyNumber;
		BorrowAbleDate = borrowAbleDate;
	}

	public int getIdCopyNumber() {
		return IdCopyNumber;
	}
	
	public int getBorrowAbleDate() {
		return BorrowAbleDate;
	}
	
	public void setIdCopyNumber(int idCopyNumber) {
		IdCopyNumber = idCopyNumber;
	}
	
	public void setBorrowAbleDate(int borrowAbleDate) {
		BorrowAbleDate = borrowAbleDate;
	}
}
