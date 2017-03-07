package com.mum.edu.library.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book implements Serializable  {
	private static final long serialVersionUID = 1L;

	private String title;
	private String isbnNumber;
	private Set<Author> author;
	private boolean availability;
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

	@XmlAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlAttribute
	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	@XmlAttribute
	public Set<Author> getAuthor() {
		return author;
	}

	public void setAuthor(Set<Author> author) {
		this.author = author;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@XmlAttribute
	public Set<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(Set<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

}
