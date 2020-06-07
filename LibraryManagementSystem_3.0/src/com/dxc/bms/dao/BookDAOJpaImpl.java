package com.dxc.bms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.dxc.bms.exception.BookException;
import com.dxc.bms.model.Book;

public class BookDAOJpaImpl implements BookDAO 
{
	
	
	static
	{
		em=Persistence.createEntityManagerFactory("mysqlPU").createEntityManager();
	}
	private static EntityManager em;

	
	@Override
	public void addItem(Book book) throws BookException 
	{
		EntityTransaction txn=em.getTransaction();
		if(book!=null)
		{
			
			txn.begin();
			em.persist(book);
			txn.commit();
			//em.flush();
		}
		

	}

	@Override
	public void deleteItem(int bcode) throws BookException
	{
		EntityTransaction txn=em.getTransaction();
		Book book=em.find(Book.class,bcode);
		if(book!=null)
		{
			
			txn.begin();
			em.persist(book);
			txn.commit();
			//em.flush();
		}else
		{
			throw new BookException("no book found");
		}
	}

	@Override
	public List<Book> getAllItems() throws BookException {
		TypedQuery<Book> qry=em.createNamedQuery("AllIBooksQry",Book.class);
	    
		return qry.getResultList();
	}

	@Override
	public Book getItemByBcode(int bcode) throws BookException {
		TypedQuery<Book>  qry=em.createNamedQuery("bookQry",Book.class);
		qry.setParameter("bcode",bcode);
		
		return qry.getFirstResult()>0? qry.getSingleResult():null;
	}

}
