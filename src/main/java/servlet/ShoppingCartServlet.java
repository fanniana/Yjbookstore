package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import dao.ShoppingCartDao;
import dao.UserDao;
import dao.impl.OrderDaoImpl;
import dao.impl.ShoppingCartDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Order;
import entity.ShoppingCart;
import entity.User;

public class ShoppingCartServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		String username = (String) session.getAttribute("username");
		if(username == null){
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		UserDao dao = new UserDaoImpl();
		User user = dao.getSingleUser(username);
		int uid = user.getUid();
		ShoppingCartDao dao1 = new ShoppingCartDaoImpl();
		List<ShoppingCart> itemslist = dao1.personalCart(uid);
		request.setAttribute("itemslist",itemslist);
		
		OrderDao dao2 = new OrderDaoImpl();		
		List<Order> orderlist = dao2.getOrderPerson(uid);
		request.setAttribute("orderlist",orderlist);
		request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
	}

}
