package com.mum.edu.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Books", propOrder = { "book" })
@XmlRootElement(name = "Books")
public class Books implements Serializable {
	
	public Books() {
		books = new ArrayList<>();
	}

	private static final long serialVersionUID = 1L;
	@XmlElement(name = "Book", required = true)
	protected List<Book> books;

	public List<Book> getBook() {
		return books;
	}

	public void setBook(List<Book> books) {
		this.books = books;
	}

}
