package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;

public class PayMoneyServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String oids = request.getParameter("oid");
		int oid = Integer.parseInt(oids);
		OrderDao dao = new OrderDaoImpl();
		dao.payMoney(oid);
		request.getRequestDispatcher("shoppingcart.do").forward(request,response);
		
	}
}
