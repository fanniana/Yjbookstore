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

public class UpdateUserInfoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String username1 = request.getParameter("username1");
		String tel = request.getParameter("tel1");
		String address = request.getParameter("address1");
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		UserDao dao = new UserDaoImpl();
		dao.updateUserInfo(username, username1, tel, address);
		
		username=username1;
		session.setAttribute("username", username);
		
		User user = dao.getSingleUser(username);
		request.setAttribute("user", user);
		request.getRequestDispatcher("userinfo.jsp").forward(request,response);
	}
}
