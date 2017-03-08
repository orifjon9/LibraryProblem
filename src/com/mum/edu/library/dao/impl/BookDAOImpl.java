package com.mum.edu.library.dao.impl;

import java.io.File;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

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
	    try {
	    	File file = new File("D:\\Java\\Books.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(file);

	    	//optional, but recommended
	    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	    	doc.getDocumentElement().normalize();
	    	NodeList nList = doc.getElementsByTagName("Book");
	    	
	    	for (int temp = 0; temp < nList.getLength(); temp++) {

	    		Node nNode = nList.item(temp);

	    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	    			Element eElement = (Element) nNode;
	    			
	    			Integer borrow = Integer.parseInt(eElement.getElementsByTagName("borrowAbleDate").item(0).getTextContent());
	    			Integer id = Integer.parseInt(eElement.getElementsByTagName("idCopyNumber").item(0).getTextContent());
	    			Integer sum = borrow + id;
	    			
    			
		    		Book book = new Book(eElement.getElementsByTagName("title").item(0).getTextContent(), 
		    				eElement.getElementsByTagName("isbnNumber").item(0).getTextContent(),
		    				Boolean.valueOf(eElement.getElementsByTagName("availability").item(0).getTextContent()));
		    		book.getAuthor().add(new Author(eElement.getElementsByTagName("firstName").item(0).getTextContent(),
		    				eElement.getElementsByTagName("lastName").item(0).getTextContent(),
		    				new Address(eElement.getElementsByTagName("city").item(0).getTextContent(), 
		    						eElement.getElementsByTagName("state").item(0).getTextContent(),
		    						eElement.getElementsByTagName("street").item(0).getTextContent(),
		    						eElement.getElementsByTagName("zip").item(0).getTextContent()),
		    				eElement.getElementsByTagName("phoneNumber").item(0).getTextContent(), 
		    				eElement.getAttribute("credentials"), 
		    				eElement.getAttribute("shortbio")));
		    		book.getBookCopies().add(new BookCopy(Integer.parseInt(eElement.getElementsByTagName("borrowAbleDate").item(0).getTextContent()), 
		    				Integer.parseInt(eElement.getElementsByTagName("idCopyNumber").item(0).getTextContent())));
//		    		book.getAuthor().add(new Author("Jack","London",new Address("407 SD st", "Fairfield", "Iowa", "52556"), 
//							"123456","Certificate","Good"));
//		    		book.getBookCopies().add(new BookCopy(1002, 7));
		    		
		    		lstBook.add(book);
	    		}

	    	}
	    } catch (Exception e) {
	    		e.printStackTrace();
	    }		
		
//		Book book1 = new Book("TDo Androids Dream of Electric Sheep?", "12346", true);
//		book1.getAuthor().add(new Author("Rick","Deckard",new Address("408 SD st", "Fairfield", "Iowa", "52556"), 
//				"123456","Certificate","Fair"));
//		book1.getBookCopies().add(new BookCopy(1001, 21));
//		Book book2 = new Book("To Kill A Mockingbird", "12347", true);
//		book2.getAuthor().add(new Author("Happer","Lee",new Address("409 SD st", "Fairfield", "Iowa", "52556"), 
//				"123456","Certificate","Fair"));
//		book2.getBookCopies().add(new BookCopy(1002, 7));
//		
//		lstBook.add(book1);
//		lstBook.add(book2);
		
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
	
	@Override
	public void addCopy(Book book){
		List<Book> lstBook = read();
		for(Book b:lstBook)
		{
			if(b.getIsbnNumber().equals(book.getIsbnNumber()))
			{
				save(book);				
			}
			else
				save(b);				
		}
		
		return;
	}
}
