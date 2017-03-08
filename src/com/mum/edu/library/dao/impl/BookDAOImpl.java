package com.mum.edu.library.dao.impl;

import java.io.File;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import com.mum.edu.library.constant.Constant;
import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Author;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.Books;
import com.mum.edu.library.rule.ApplicationException;

public class BookDAOImpl implements BookDAO {
	JAXBContext jaxbContext = null;
	File file = new File("D:\\Java\\Books.xml");
	
	@Override
	public void save(Book bookToSave) throws ApplicationException {
		try {
			List<Book> lstBook = read();

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			lstBook.add(bookToSave);
			flush(lstBook, jaxbMarshaller);

		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}
	}
	
	private void flush(List<Book> lstBook, Marshaller jaxbMarshaller) throws PropertyException, JAXBException {
		Books books = new Books();
		books.getBook().addAll(lstBook);
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(books, file);
		jaxbMarshaller.marshal(books, System.out);
	}

	@Override
	public List<Book> read() throws ApplicationException{
		Books books = new Books();
		
		try {
			jaxbContext = JAXBContext.newInstance(Books.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			books = (Books) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}
		return books.getBook();

	}
	
	@Override
	public Book searchBook(String idBook) throws ApplicationException {
		try {
			List<Book> lstBook = read();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			for (Book b : lstBook) {
				if (b.getIsbnNumber().equals(idBook)) 
					return b;
			}
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}

		return null;
	}
	
	@Override
	public void addCopy(Book book) throws ApplicationException{
		try {
			List<Book> lstBook = read();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			for (Book b : lstBook) {
				if (b.getIsbnNumber().equals(book.getIsbnNumber())) {
					b.copyFrom(book);
				}					
			}

			flush(lstBook, jaxbMarshaller);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}	

	}
}
