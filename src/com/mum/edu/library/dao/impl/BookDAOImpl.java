package com.mum.edu.library.dao.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mum.edu.library.dao.BookDAO;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.Books;

public class BookDAOImpl implements BookDAO {
	
	/* 
	 * Need to refactor
	 */
	@Override
	public void save(Book bookToSave) {
		JAXBContext jaxbContext = null;
		Books books = new Books();
		File file = new File("D:\\Java\\Books.xml");
		try {

			jaxbContext = JAXBContext.newInstance(Books.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			books = (Books) jaxbUnmarshaller.unmarshal(file);
			books.getBook().add(bookToSave);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		try {
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(books, file);
			jaxbMarshaller.marshal(books, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}