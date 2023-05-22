package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import entity.Book;

public class HomeLoadServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException	{
		System.out.println("到这了吗？");
		BookDao dao = new BookDaoImpl();
		List<Book> booklist1 =dao.getBook1();
		List<Book> booklist2 =dao.getBook2();

		request.setAttribute("booklist1",booklist1);
		request.setAttribute("booklist2",booklist2);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
}
