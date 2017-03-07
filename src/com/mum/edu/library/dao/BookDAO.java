package com.mum.edu.library.dao;

import java.util.*;
import com.mum.edu.library.model.Book;

public interface BookDAO {
	
	public void save(Book book);
	public List<Book> read();
	public Book searchBook(String idBook);
}
