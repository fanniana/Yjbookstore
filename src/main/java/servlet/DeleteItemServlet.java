package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShoppingCartDao;
import dao.UserDao;
import dao.impl.ShoppingCartDaoImpl;
import dao.impl.UserDaoImpl;
import entity.User;

public class DeleteItemServlet  extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		UserDao dao = new UserDaoImpl();
		User user = dao.getSingleUser(username);
		int uid = user.getUid();
				
		String bids = request.getParameter("bid");
		int bid = Integer.parseInt(bids);
		
		ShoppingCartDao dao1 = new ShoppingCartDaoImpl();
		dao1.deleteitem(uid,bid);
		request.getRequestDispatcher("shoppingcart.do").forward(request, response);
	}

}
