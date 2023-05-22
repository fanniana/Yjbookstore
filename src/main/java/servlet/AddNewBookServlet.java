package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.C3P0Util;

public class AddNewBookServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String bname = request.getParameter("bname");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String types = request.getParameter("types");
		String introduction = request.getParameter("introduction");
		String image = request.getParameter("image");
		String price1 = request.getParameter("price");
		double price = Double.parseDouble(price1);
		String count1 = request.getParameter("count");
		int count = Integer.parseInt(count1);
		String recommend1 = request.getParameter("recommend");
		int recommend = 0;
		if(recommend1 != null) {
			recommend = 1;
		}
		System.out.println(bname+author+publisher+types+introduction+image+price+count+recommend);
					
		try {
			Connection conn = C3P0Util.getConn();	
			Statement stmt=conn.createStatement();
			String sql= "insert into book(bname,author,publisher,types,introduction,image,price,count,sellcount,recommend) values("
					+ "'"+bname+"',"
					+ "'"+author+"',"
					+ "'"+publisher+"',"
					+ "'"+types+"',"
					+ "'"+introduction+"',"
					+ "'"+image+"',"
					+ "'"+price+"',"
					+ "'"+count+"',0,"
							+ "'"+recommend+"')";
			stmt.executeUpdate(sql);
			stmt.close();
			C3P0Util.closeConn(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		request.getRequestDispatcher("bookmanager.jsp").forward(request,response);
		
	}
}
