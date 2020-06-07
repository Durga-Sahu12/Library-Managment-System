package com.dxc.bms.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dxc.bms.exception.BookException;
import com.dxc.bms.model.Book;
import com.dxc.bms.service.BookService;
import com.dxc.bms.service.BookServiceImpl;

/**
 * Servlet implementation class LibraryController
 */
@WebServlet({"/viewBooks","/addBook","/confirmAddBook","/deleteBook"})
public class LibraryController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 String path = request.getServletPath();
		String view=null;
		try {
			BookService bookService= new BookServiceImpl();
			switch(path)
			{
			case "/viewBooks":
			view ="booksPage.jsp";
			request.setAttribute("books",bookService.getAllItems());
			break;
			
			case "/addBook":
				view="bookFromPage.jsp";
				break;
				
			case "/confirmAddBook":
				Book book=new Book();
				
				book.setBcode(Integer.parseInt(request.getParameter("bcode")));
				book.setBname(request.getParameter("bname"));
				book.setAuthor(request.getParameter("author"));
				book.setIssueDate(LocalDate.parse(request.getParameter("issueDate")));
				
				bookService.addItem(book);
				
				view ="index.jsp";
				request.setAttribute("msg","Books  data is saved with bcode: "+book.getBcode( ));
				break;
				
             case "/deleteBook":
            	
            	int bcode= Integer.parseInt(request.getParameter("bcode"));
            	
            	bookService.deleteItem(bcode);
            	
            	view="index.jsp";
            	request.setAttribute("msg","Book is deleted having bcode: "+bcode);
              break;
			}
			
		}
		catch (BookException e)
		{
			view = "errorPage.jsp";
            request.setAttribute("errMsg", e.getMessage());
		}
		 request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
