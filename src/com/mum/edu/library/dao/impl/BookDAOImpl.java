package com.mum.edu.library.dao.impl;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import com.mum.edu.library.api.CommonAPI;
import com.mum.edu.library.constant.Constant;
import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.Books;
import com.mum.edu.library.rule.ApplicationException;

public class BookDAOImpl implements BookDAO {
	JAXBContext jaxbContext = null;
	private final File file = new File(CommonAPI.findExactlyDataBase(Constant.BOOKS_FILE));
	
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
		List<Book> lstBook = read();
		for (Book b : lstBook) {
			if (b.getIsbnNumber().equals(idBook)) 
				return b;
		}
		return null;
	}
	
	@Override
	public List<Book> searchBooks(String isbn) throws ApplicationException {
		List<Book> lstBook = read();
		for(Book b:lstBook)
		{
			if(b.getIsbnNumber().indexOf(isbn) < 0)
				lstBook.remove(b);
		}
		
		return lstBook;
	}
	
	@Override
	public void editCopy(Book book) throws ApplicationException{
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
