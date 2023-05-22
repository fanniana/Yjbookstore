package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import dao.BookDao;
import entity.Book;

public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> getBook1() {
		// TODO Auto-generated method stub
		
		List<Book> booklist = new ArrayList<Book>();
		
		try {			
			Connection conn = C3P0Util.getConn();			
			Statement stmt=conn.createStatement();
			String sql="select * from book where recommend=1";				
			ResultSet resultSet=stmt.executeQuery(sql);

			while (resultSet.next()) {
				int bid = resultSet.getInt("bid");
				String bname = resultSet.getString("bname");
				String author = resultSet.getString("author");
				String image = resultSet.getString("image");
				double price = resultSet.getDouble("price");
				
				Book book=new Book();
				book.setBid(bid);
				book.setBname(bname);
				book.setAuthor(author);
				book.setImage(image);
				book.setPrice(price);
				
				booklist.add(book);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return booklist;
	}

	@Override
	public List<Book> getBook2() {
		// TODO Auto-generated method stub
		List<Book> booklist = new ArrayList<Book>();
		
		try {			
			Connection conn = C3P0Util.getConn();
			
			Statement stmt=conn.createStatement();
			String sql="select * from book order by sellcount DESC";	
			
			ResultSet resultSet=stmt.executeQuery(sql);
			
			int count1=0;
			while (resultSet.next()&&count1<4) {
				int bid = resultSet.getInt("bid");
				String bname = resultSet.getString("bname");
				String author = resultSet.getString("author");
				String image = resultSet.getString("image");
				double price = resultSet.getDouble("price");
				int sellcount = resultSet.getInt("sellcount");
				
				Book book=new Book();
				book.setBid(bid);
				book.setBname(bname);
				book.setAuthor(author);
				book.setImage(image);
				book.setPrice(price);
				book.setSellcount(sellcount);
				
				booklist.add(book);
				count1++;
			}	
			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return booklist;
	}
	
	public int max2(int a,int b) {
		return(a >= b ? a:b); 
	}
	public int getmax4(int a,int b,int c,int d) {
		return(max2(max2(max2(a,b),c),d));
	}
	
	@Override
	public List<Book> getBook3(int uid) {
		// TODO Auto-generated method stub
		List<Book> booklist = new ArrayList<Book>();	
		String recommend1 = "";
		String recommend2 = "";

		try {	
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			if(uid == 0) {
				recommend1="休闲";
				recommend2="少儿";
			}else {
				String sql="select bid from orderdetail where uid='"+uid+"'";	
				System.out.println(sql);
				ResultSet resultSet=stmt.executeQuery(sql);
				int bid= 0;
				int type1 = 0;
				int type2 = 0;
				int type3 = 0;
				int type4 = 0;
				String type = "";
				
				List bids=new ArrayList<>();
				while(resultSet.next()) {
					bids.add(resultSet.getInt(1));
				}

	            for (int i = 0; i < bids.size(); i++) {
	            	bid=(int) bids.get(i);
					sql = "select types from book where bid='"+bid+"'";	
					System.out.println(sql);
					ResultSet resultSet1=stmt.executeQuery(sql);
					if(resultSet1.next()) {
						type = resultSet1.getString(1);
						switch(type) {
						case "文学":type1++;break;
						case "科技":type2++;break;
						case "休闲":type3++;break;
						case "少儿":type4++;break;					
						}
					}
					resultSet1.close();
	            }

				int max = getmax4(type1,type2,type3,type4);
				
				if(type1 == max) {
					recommend1="文学";
				}else if(type2 == max) {
					recommend1="科技";
				}else if(type3 == max) {
					recommend1="休闲";
				}else {
					recommend1="少儿";
				}
				
				System.out.println(recommend1);
				
				sql="SELECT bid FROM orderdetail LIMIT 1";	
				int bid2 = 10 ;
				System.out.println(sql);
				ResultSet resultSet2=stmt.executeQuery(sql);
				if(resultSet2.next()) {
					bid2=resultSet2.getInt(1);
				}
	
				sql = "select types from book where bid='"+bid2+"'";	
				ResultSet resultSet3=stmt.executeQuery(sql);
				if(resultSet3.next()) {
					recommend2=resultSet3.getString(1);
				}
				
				if(recommend2.equals(recommend1)) {
					recommend2="少儿";
				}
				System.out.println(recommend2);
			}
			
			String sql1="select * from book where types='"+recommend1+"'";			
			ResultSet resultSet4=stmt.executeQuery(sql1);			
			
			int count1=0;
			while (resultSet4.next()&&count1<2) {
				String bname = resultSet4.getString("bname");
				String author = resultSet4.getString("author");
				String introduction = resultSet4.getString("introduction");
				int count = resultSet4.getInt("count");
				String image = resultSet4.getString("image");
				int sellcount = resultSet4.getInt("sellcount");
				int recommend = resultSet4.getInt("recommend");
				double price = resultSet4.getDouble("price");
				
				Book book=new Book();
				book.setBname(bname);
				book.setAuthor(author);
				book.setIntroduction(introduction);
				book.setCount(sellcount);
				book.setImage(image);
				book.setSellcount(sellcount);
				book.setRecommend(recommend);
				book.setPrice(price);
				
				count1++;
				booklist.add(book);
			}	
			
			String sql2="select * from book where types='"+recommend2+"'";			
			ResultSet resultSet5=stmt.executeQuery(sql2);			
			
			int count2=0;
			while (resultSet5.next()&&count2<2) {
				String bname = resultSet5.getString("bname");
				String author = resultSet5.getString("author");
				String introduction = resultSet5.getString("introduction");
				int count = resultSet5.getInt("count");
				String image = resultSet5.getString("image");
				int sellcount = resultSet5.getInt("sellcount");
				int recommend = resultSet5.getInt("recommend");
				double price = resultSet5.getDouble("price");
				
				Book book=new Book();
				book.setBname(bname);
				book.setAuthor(author);
				book.setIntroduction(introduction);
				book.setCount(sellcount);
				book.setImage(image);
				book.setSellcount(sellcount);
				book.setRecommend(recommend);
				book.setPrice(price);
				
				count2++;
				booklist.add(book);
			}	
			C3P0Util.closeConn(conn);
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return booklist;
	}

	@Override
	public List<Book> findBookBytype(String type) {
		// TODO Auto-generated method stub
		List<Book> booklist = new ArrayList<Book>();
		
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select * from book where types='"+type+"'";			
			ResultSet resultSet=stmt.executeQuery(sql);

			while (resultSet.next()) {
				String bname = resultSet.getString("bname");
				String author = resultSet.getString("author");
				String image = resultSet.getString("image");
				double price = resultSet.getDouble("price");
				
				Book book=new Book();
				book.setBname(bname);
				book.setAuthor(author);
				book.setImage(image);
				book.setPrice(price);
				
				booklist.add(book);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return booklist;
	}

	@Override
	public Book findBookByName(String bname) {
		// TODO Auto-generated method stub
		Book book=new Book();
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select * from book where bname='"+bname+"'";			
			ResultSet resultSet=stmt.executeQuery(sql);

			if (resultSet.next()) {
				int bid = resultSet.getInt("bid");
				String bookname = resultSet.getString("bname");
				String author = resultSet.getString("author");
				String publisher = resultSet.getString("publisher");
				String type = resultSet.getString("types");
				String introduction = resultSet.getString("introduction");
				String image = resultSet.getString("image");
				double price = resultSet.getDouble("price");
				int count = resultSet.getInt("count");
				int sellcount = resultSet.getInt("sellcount");
				int recommend = resultSet.getInt("recommend");
				
				book.setBid(bid);
				book.setBname(bname);
				book.setPublisher(publisher);
				book.setType(type);
				book.setIntroduction(introduction);
				book.setAuthor(author);
				book.setImage(image);
				book.setPrice(price);
				book.setCount(count);
				book.setSellcount(sellcount);
				book.setRecommend(recommend);
				
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return book;
	}

	
	@Override
	public List<Book> findBookSameType(String bname) {
		// TODO Auto-generated method stub
		List<Book> booklist = new ArrayList<Book>();
		
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select * from book where bname='"+bname+"'";			
			ResultSet resultSet=stmt.executeQuery(sql);
			String type="";
			if(resultSet.next()) {
				type = resultSet.getString("types");
			}
			String sql1="select * from book where types='"+type+"'and bname!='"+bname+"'";
			ResultSet resultSet1=stmt.executeQuery(sql1);	
			while (resultSet1.next()) {
				int bid = resultSet1.getInt("bid");
				String bname1 = resultSet1.getString("bname");
				String author = resultSet1.getString("author");
				String image = resultSet1.getString("image");
				double price = resultSet1.getDouble("price");
				
				Book book=new Book();
				book.setBid(bid);
				book.setBname(bname1);
				book.setAuthor(author);
				book.setImage(image);
				book.setPrice(price);
				
				booklist.add(book);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
			resultSet1.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return booklist;
	}

	@Override
	public Book findBookByStr(String str) {
		// TODO Auto-generated method stub
		Book book=new Book();
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select * from book where bname like '%"+str+"%'";			
			ResultSet resultSet=stmt.executeQuery(sql);

			if (resultSet.next()) {
				String bname = resultSet.getString("bname");
				String author = resultSet.getString("author");
				String publisher = resultSet.getString("publisher");
				String type = resultSet.getString("types");
				String introduction = resultSet.getString("introduction");
				String image = resultSet.getString("image");
				double price = resultSet.getDouble("price");
				
				book.setBname(bname);
				book.setPublisher(publisher);
				book.setType(type);
				book.setIntroduction(introduction);
				book.setAuthor(author);
				book.setImage(image);
				book.setPrice(price);
				System.out.println("找到了");
			}else {
				String sql1="select * from book where author like '%"+str+"%'";			
				ResultSet resultSet1=stmt.executeQuery(sql1);

				if (resultSet1.next()) {
					String bname = resultSet1.getString("bname");
					String author = resultSet1.getString("author");
					String publisher = resultSet1.getString("publisher");
					String type = resultSet1.getString("types");
					String introduction = resultSet1.getString("introduction");
					String image = resultSet1.getString("image");
					double price = resultSet1.getDouble("price");
					
					book.setBname(bname);
					book.setPublisher(publisher);
					book.setType(type);
					book.setIntroduction(introduction);
					book.setAuthor(author);
					book.setImage(image);
					book.setPrice(price);
					System.out.println("找到了");
				}
				resultSet1.close();
			}
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return book;
	}

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		List<Book> booklist = new ArrayList<Book>();
		
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select * from book";				
			ResultSet resultSet=stmt.executeQuery(sql);

			while (resultSet.next()) {
				int bid = resultSet.getInt("bid");
				String bname = resultSet.getString("bname");
				String author = resultSet.getString("author");
				String type = resultSet.getString("types");
				int count = resultSet.getInt("count");
				String image = resultSet.getString("image");
				int sellcount = resultSet.getInt("sellcount");
				int recommend = resultSet.getInt("recommend");
				double price = resultSet.getDouble("price");
				
				Book book=new Book();
				
				book.setBid(bid);
				book.setBname(bname);
				book.setAuthor(author);
				book.setType(type);
				book.setCount(count);
				book.setImage(image);
				book.setSellcount(sellcount);
				book.setRecommend(recommend);
				book.setPrice(price);

				booklist.add(book);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return booklist;
	}

	@Override
	public void deleteABook(int bid) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql = "delete from book where bid='"+bid+"'";
			stmt.executeUpdate(sql);
			C3P0Util.closeConn(conn);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
