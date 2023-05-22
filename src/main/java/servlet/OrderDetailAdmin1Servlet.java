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
import entity.OrderDetail;
import entity.User;

public class OrderDetailAdmin1Servlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String oid1 = request.getParameter("oid");
		int oid = Integer.parseInt(oid1);
		
		OrderDao dao = new OrderDaoImpl();	
		Order orderinfo = dao.getAOrder(oid);
		
		User user = dao.getUserByOid(oid);
		
		List<OrderDetail> orderdetaillist = dao.getOrderDetail(oid);
		
		request.setAttribute("orderinfo",orderinfo);
		request.setAttribute("user",user);
		request.setAttribute("orderdetaillist",orderdetaillist);
		request.getRequestDispatcher("orderdetailadmin1.jsp").forward(request, response);
		
	}

}
