package servlet;

import java.io.IOException;
import java.sql.Connection;
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

public class RegistServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String tel = request.getParameter("tel");
		String adress = request.getParameter("adress");
		

		try {
			
			DataSource ds = new ComboPooledDataSource();
			Connection conn = ds.getConnection();
			
			Statement stmt = conn.createStatement();
			String sql1="select count(*) from user where username='"+username+"'";	
			
			ResultSet resultSet1=stmt.executeQuery(sql1);
			int count=0;
			while (resultSet1.next()) {
				count=resultSet1.getInt(1);
			}
			
			Boolean flag=true;
			if(username.equals("")) {
				String useremptyError = "用户名不能为空";
				request.setAttribute("useremptyError", useremptyError);
				flag=false;
			}
			
			if(password.equals("")) {
				String passwordemptyError = "密码不能为空";
				request.setAttribute("passwordemptyError", passwordemptyError);
				flag=false;
			}
			
			if(flag) {
				if(count!=0) {
					String usernameError = "用户名已存在";
					request.setAttribute("usernameError", usernameError);
					request.getRequestDispatcher("regist.jsp").forward(request,response);
				}else if(!password.equals(repassword)){
					String passwordError = "两次密码不一致";
					request.setAttribute("passwordError", passwordError);			
					request.getRequestDispatcher("regist.jsp").forward(request,response);
				}else {
					String sql2 = "insert into user(username,password,state,tel,address) values("
							+ "'"+username+"',"
									+ "'"+password+"',"
										+0+","
											+ "'"+tel+"',"
												+ "'"+adress+"')";
					stmt.executeUpdate(sql2);
					conn.close();
					stmt.close();
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					request.getRequestDispatcher("homeload.do").forward(request,response);
				}
			}else {
				request.getRequestDispatcher("regist.jsp").forward(request,response);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
