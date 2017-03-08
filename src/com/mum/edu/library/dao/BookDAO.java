package com.mum.edu.library.dao;

import java.util.*;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.rule.ApplicationException;

public interface BookDAO {
	
	public void save(Book book) throws ApplicationException;
	public List<Book> read() throws ApplicationException;
	public Book searchBook(String idBook) throws ApplicationException;
	public void addCopy(Book book) throws ApplicationException;
}
