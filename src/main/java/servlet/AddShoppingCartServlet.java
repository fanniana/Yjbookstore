package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import dao.ShoppingCartDao;
import dao.UserDao;
import dao.impl.BookDaoImpl;
import dao.impl.ShoppingCartDaoImpl;
import dao.impl.UserDaoImpl;
import entity.User;

public class AddShoppingCartServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("到这了吗！");
		String pages = request.getParameter("page");
		String bids = request.getParameter("bid");
		
		try {
		    int bid = Integer.parseInt(bids);
		    int page = Integer.parseInt(pages);
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			
			UserDao dao = new UserDaoImpl();
			ShoppingCartDao dao1 = new ShoppingCartDaoImpl();
			
			if(username!=null) {
				User user = dao.getSingleUser(username);
				int uid = user.getUid();
				dao1.addNewRecord(uid,bid);
				String suinfo = "添加成功！";
				request.setAttribute("suinfo", suinfo);
				switch(page) {
				case 0 :request.getRequestDispatcher("index.jsp").forward(request, response);break;
				case 1 :request.getRequestDispatcher("index.jsp").forward(request, response);break;
				case 2 :request.getRequestDispatcher("index.jsp").forward(request, response);break;
				case 3 :request.getRequestDispatcher("index.jsp").forward(request, response);break;
				case 4 :request.getRequestDispatcher("index.jsp").forward(request, response);break;
				
				default : request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}else {
				request.getRequestDispatcher("login.jsp").forward(request,response);
				//response.sendRedirect("login.jsp");
			}
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}

		
		
	}
}
