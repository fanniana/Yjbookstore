package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.impl.UserDaoImpl;

public class GrantPermissionsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
					
		String uids = request.getParameter("uid");
		int uid = Integer.parseInt(uids);
		String states = request.getParameter("state");
		int state = Integer.parseInt(states);
		UserDao dao = new UserDaoImpl();
		if(!dao.grantPerm(uid,state)) {
			String failtochange="不能修改该用户的权限";
			request.setAttribute("failtochange", failtochange);
		}
		request.getRequestDispatcher("usermanager.jsp").forward(request,response);
		
	}
}
