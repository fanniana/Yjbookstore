package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import entity.Book;

public class SingleBookServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("到这了吗？");
		String bname = request.getParameter("bname");
		System.out.println(bname);
		BookDao dao = new BookDaoImpl();
		Book book = dao.findBookByName(bname);
		List<Book> booklist3 =dao.findBookSameType(bname);
		request.setAttribute("book",book);
		request.setAttribute("booklist3",booklist3);
		request.getRequestDispatcher("singlebook.jsp").forward(request, response);
		
	}
}
