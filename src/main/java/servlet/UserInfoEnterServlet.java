package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;

public class UserInfoEnterServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username != null){
			//考虑转发和重定向的区别
			UserDao dao = new UserDaoImpl();
			User user = dao.getSingleUser(username);
			request.setAttribute("user", user);
			request.getRequestDispatcher("userinfo.jsp").forward(request,response);
			//response.sendRedirect("userinfo.jsp");
		}else {
			//考虑转发和重定向的区别
			request.getRequestDispatcher("login.jsp").forward(request,response);
			//response.sendRedirect("login.jsp");
		}
	}
	
}
