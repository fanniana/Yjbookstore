package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import entity.Book;

public class TypeDoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("到这了吗？");
		String type = request.getParameter("type");
		System.out.println(type);
		BookDao dao = new BookDaoImpl();
		List<Book> booklist2 =dao.findBookBytype(type);
		request.setAttribute("booklist2",booklist2);
		switch(type) {
		case "文学" :request.getRequestDispatcher("booktype1.jsp").forward(request, response);break;
		case "科技" :request.getRequestDispatcher("booktype2.jsp").forward(request, response);break;
		case "休闲" :request.getRequestDispatcher("booktype3.jsp").forward(request, response);break;
		case "少儿" :request.getRequestDispatcher("booktype4.jsp").forward(request, response);break;
		default : request.getRequestDispatcher("homeload.do").forward(request, response);
		}

	}

}
