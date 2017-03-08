package com.mum.edu.library.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Book", propOrder = {"title","isbnNumber","availability", "author","bookCopies" })
@XmlRootElement(name = "Book")
public class Book implements Serializable  {
	private static final long serialVersionUID = 1L;

	private String title;
	private String isbnNumber;
	
	@XmlElement(name = "Author", required = true)
	private Set<Author> author;
	private boolean availability;
	@XmlElement(name = "BookCopy", required = true)
	private Set<BookCopy> bookCopies;
	
	public Book() {
	}

	public Book(String title, String isbnNumber, boolean availability) {
		this.title = title;
		this.isbnNumber = isbnNumber;
		this.author = new HashSet<>();
		this.availability = availability;
		this.bookCopies = new HashSet<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public Set<Author> getAuthor() {
		return author;
	}

	public void setAuthor(Set<Author> author) {
		this.author = author;
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Set<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(Set<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public void copyFrom(Book editBook) {
		this.setTitle(editBook.getTitle());
		this.setIsbnNumber(editBook.getIsbnNumber());
		this.setAvailability(editBook.getAvailability());		
		this.setAuthor(editBook.getAuthor());
		this.setBookCopies(editBook.getBookCopies());		
	}
}
