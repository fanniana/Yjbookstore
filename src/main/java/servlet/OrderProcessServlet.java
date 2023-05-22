package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import entity.Order;

public class OrderProcessServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		OrderDao dao2 = new OrderDaoImpl();		
		List<Order> orderlist = dao2.getOrderAllToDo();
		request.setAttribute("orderlist",orderlist);
		request.getRequestDispatcher("orderprocess.jsp").forward(request, response);
	}
	
}
