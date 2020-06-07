package com.dxc.bms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dxc.bms.dao.BookDAO;
import com.dxc.bms.dao.BookDAOImpl;
import com.dxc.bms.dao.BookDAOJpaImpl;
import com.dxc.bms.exception.BookException;
import com.dxc.bms.model.Book;

public class BookServiceImpl implements BookService 
{
	private BookDAO bookDAO;
	
	 public BookServiceImpl() throws BookException
	 {
		 this.bookDAO=new  BookDAOJpaImpl();
	 }
	
	/*bcode can not be zero or negative ,bcode should e unique**/
	private boolean isValidBcode(int bcode) throws BookException
	{
		return bcode  >0 && (bookDAO.getItemByBcode(bcode)==null);
	}
	
	/*name can not null ,can not be empty ,min of 3chars and max of 20 chrs*/
	private boolean isValidBname(String bname)
	{
		return bname!=null && bname.length()>3 &&  bname .length()<30;
	}
	
	/* author  name can not be null n empty ,min 5chars r their**/
	private boolean isValidAuthor(String author)
	{
		return author!=null && author.length()>3 && author.length()<20;
	}
	private boolean isValidIssueDate(LocalDate  issueDate)
	{
		LocalDate today=LocalDate.now();
		 // issueDate.isbefore or today;
		return !issueDate.isBefore(today)  || issueDate.equals(today) ;
	}
	
	private boolean isValidBookItem(Book book) throws BookException 
	{
		boolean isValid=true;
		if(book==null)
		{
			isValid=false;
			throw new BookException("Books can not null");
		}
		List<String> errMsgs=new ArrayList<String>();
		
		if(!isValidBcode(book.getBcode())) {
			errMsgs.add("Err: bcode can not be zero and negative,and cannot  be repeatitve");
		}
		
		if(!isValidBname(book.getBname()))
		{
			errMsgs.add("Err: Name cannot be blank and must be of 3 to 20 chars in length");
		}
		
		if(!isValidAuthor(book.getAuthor()))
		{
			errMsgs.add("Err: AuthorName cannot be blank and must be of 3 to 20 chars in length");
		}
		
		if(!isValidIssueDate(book.getIssueDate()))
		{
			errMsgs.add("Err: Issuedate cannot be future date");
		}
		if(errMsgs.size()>0)
		{
			isValid=false;
			throw new BookException(errMsgs.toString());
		}
		return isValid;
	}
	
	

	@Override
	public void addItem(Book book) throws BookException 
	{

   bookDAO.addItem(book);
	}

	@Override
	public void deleteItem(int bcode) throws BookException 
	{
		bookDAO.deleteItem(bcode);

	}

	@Override
	public List<Book> getAllItems() throws BookException {
		
		return bookDAO.getAllItems();
	}

	@Override
	public Book getItemByBcode(int bcode) throws BookException {
		
		return bookDAO.getItemByBcode(bcode);
	}

}
