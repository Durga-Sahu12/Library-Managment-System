package com.dxc.bms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dxc.bms.exception.BookException;
import com.dxc.bms.model.Book;

public class BookDAOImpl implements BookDAO
{ /** this is the responsible to store and reterive items from a file*/
	private static final String DATA_FILE_NAME="booksData.dat";//file name to store data
	
	private Map<Integer,Book>itemsMap;

	/**this constructor will loa data if the file is alrady exsist or else the empty map is initialized**/
	
	public BookDAOImpl()throws BookException
	{
		File file=new File(DATA_FILE_NAME);
		if(file.exists())
		{
			try(ObjectInputStream oin =new ObjectInputStream( new FileInputStream(file)) )
			{
				itemsMap=(Map<Integer,Book>)oin.readObject();
				}
			catch(IOException | ClassNotFoundException exp)
			{
				itemsMap=new TreeMap<>();
				throw new  BookException("unable to read data....");
			}
		}else
		{
			itemsMap=new TreeMap<>();
		}
	}
	/**saveData() method will write all the map into the file**/
	private void saveData()throws BookException
	{
		try (ObjectOutputStream oout=new ObjectOutputStream (new FileOutputStream(DATA_FILE_NAME)) )
				{
			oout.writeObject(itemsMap);
				}
		catch(IOException exp)
		{
			throw new BookException("unable to read data....");
		}
	}
	
	/**addIteam method will accept a item and stores it in the map
	 * bcode will be the key and item will be the value
	 * if item is null it will throw an BookException 
	 * **/
	
	@Override
	public void addItem(Book book)throws BookException
	{
		if(book!=null)
		{
			itemsMap.put(book.getBcode(), book);
			saveData();
		}else
		{
			throw new BookException("Null item can not be stored");
		}
		
	}
	/*deletItem will accept the bcode and removes that respectives item from map**/

	@Override
	public void deleteItem(int bcode) throws BookException {
		if(itemsMap.containsKey(bcode)) {
			
			itemsMap.remove(bcode);
			saveData();
		}
		else
		{
			throw new BookException("Book#" +bcode+"NotFound");
		}
		
		
	}
	/*getAllItems will return all the items from the map as a list*/

	@Override
	public List<Book> getAllItems() {
		
		return new ArrayList<>(itemsMap.values());
	}
/*getItemBycode will return the item respective to the given bcode if not found retruns null*/
	@Override
	public Book getItemByBcode(int bcode) {
		
		return itemsMap.get(bcode);
	}
}
	
	
	

