package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.UserDao;
import dao.impl.BookDaoImpl;
import dao.impl.UserDaoImpl;

public class DeleteABookServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		String bids = request.getParameter("bid");
		int bid = Integer.parseInt(bids);
		BookDao dao = new BookDaoImpl();
		dao.deleteABook(bid);
		request.getRequestDispatcher("bookmanager.jsp").forward(request,response);
			
	}
}
