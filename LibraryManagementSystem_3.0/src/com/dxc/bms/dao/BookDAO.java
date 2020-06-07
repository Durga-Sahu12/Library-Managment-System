package com.dxc.bms.dao;

import java.util.List;

import com.dxc.bms.model.Book;
import com.dxc.bms.exception.BookException;

public interface BookDAO 
{
	void addItem(Book book)throws BookException;
	void deleteItem(int bcode)throws BookException;
	List<Book>getAllItems() throws BookException;
	Book getItemByBcode(int bcode) throws BookException;

}
