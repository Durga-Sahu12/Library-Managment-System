package com.dxc.bms.service;

import java.util.List;

import com.dxc.bms.model.Book;
import com.dxc.bms.exception.BookException;

public interface BookService 
{
	void addItem(Book book)throws BookException;
	void deleteItem(int bcode)throws BookException;
	List<Book>getAllItems() throws BookException;
	Book getItemByBcode(int bcode) throws BookException;

}
