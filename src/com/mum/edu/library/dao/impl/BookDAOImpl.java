package com.mum.edu.library.dao.impl;

import java.io.File;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Author;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.BookCopy;
import com.mum.edu.library.model.Books;

public class BookDAOImpl implements BookDAO {
	
	@Override
	public void save(Book bookToSave) {
		JAXBContext jaxbContext = null;
		Books books = new Books();
		File file = new File("D:\\Java\\Books.xml");
		try {

			jaxbContext = JAXBContext.newInstance(Books.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			books = (Books) jaxbUnmarshaller.unmarshal(file);
			books.getBook().add(bookToSave);

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(books, file);
			jaxbMarshaller.marshal(books, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public List<Book> read(){
		List<Book> lstBook = new ArrayList<Book>();
		
		// TODO
		// Fake list book to add Copy
		
		Book book1 = new Book("TDo Androids Dream of Electric Sheep?", "12346", true);
		book1.getAuthor().add(new Author("Rick","Deckard",new Address("408 SD st", "Fairfield", "Iowa", "52556"), 
				"123456","Certificate","Fair"));
		book1.getBookCopies().add(new BookCopy(1001, 21));
		Book book2 = new Book("To Kill A Mockingbird", "12347", true);
		book2.getAuthor().add(new Author("Happer","Lee",new Address("409 SD st", "Fairfield", "Iowa", "52556"), 
				"123456","Certificate","Fair"));
		book2.getBookCopies().add(new BookCopy(1001, 7));
		
		lstBook.add(book1);
		lstBook.add(book2);
		
		return lstBook;		
	}
	
	@Override
	public Book searchBook(String idBook){
		List<Book> lstBook = read();
		for(Book b:lstBook)
		{
			if(b.getIsbnNumber().equals(idBook))
				return b;
		}
		
		return null;
	}	
}
