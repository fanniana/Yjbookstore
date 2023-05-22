package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import dao.UserDao;
import dao.impl.UserDaoImpl;

public class LoginServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String username1=request.getParameter("username1");
		String password1=request.getParameter("password1");
		
		Boolean flag=true;
		if(username1.equals("")) {
			String useremptyError = "用户名不能为空";
			request.setAttribute("useremptyError", useremptyError);
			flag=false;
		}
		
		if(password1.equals("")) {
			String passwordemptyError = "密码不能为空";
			request.setAttribute("passwordemptyError", passwordemptyError);
			flag=false;
		}
		
		UserDao dao = new UserDaoImpl();
		int count = dao.isEmptyUsername(username1);
		
		if(flag) {
			if(count==0) {
				String usernameError = "账号不存在";
				request.setAttribute("usernameError", usernameError);
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}else {	
				String password2 = dao.getPassword(username1);
				if(password1.equals(password2)) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username1);
					
					int state = dao.getState(username1);
					if( state ==0) {
						request.setAttribute("username", username1);
						request.getRequestDispatcher("homeload.do").forward(request,response);
					}else {
						response.sendRedirect("index_admin.jsp");
					}
					
				}else {
					String passwordError = "密码错误，请重新输入";
					request.setAttribute("passwordError", passwordError);			
					request.getRequestDispatcher("login.jsp").forward(request,response);
				}
			}
		}else {
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
	}

}
