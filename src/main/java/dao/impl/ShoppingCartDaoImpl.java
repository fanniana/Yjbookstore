package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import dao.ShoppingCartDao;
import entity.Book;
import entity.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

	@Override
	public void addNewRecord(int uid, int bid) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql = "insert into shoppingcart(uid,bid,countbook) values("+"'"+uid+"',"+"'"+bid+"',1)";
			stmt.executeUpdate(sql);
			stmt.close();
			C3P0Util.closeConn(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		    if(e instanceof SQLIntegrityConstraintViolationException){
		    	try {
			        String sql1 = "update shoppingcart set countbook=countbook+1 where uid='"+uid+"'and bid='"+bid+"'";	
			        Connection conn = C3P0Util.getConn();
					Statement stmt=conn.createStatement();
					stmt.executeUpdate(sql1);
					C3P0Util.closeConn(conn);
					stmt.close();
					
		    	}catch (SQLException f){
		    		f.printStackTrace();
		    	}
		    }else {
		    	e.printStackTrace();
		    }
			
		}
	}

	@Override
	public List<ShoppingCart> personalCart(int uid) {
		// TODO Auto-generated method stub
		List<ShoppingCart> itemslist = new ArrayList<ShoppingCart>();
		
		try {			
			Connection conn = C3P0Util.getConn();
			
			Statement stmt=conn.createStatement();
			String sql="select * from shoppingcart,book where shoppingcart.bid=book.bid and uid='"+uid+"'";				
			ResultSet resultSet=stmt.executeQuery(sql);

			while (resultSet.next()) {
				int bid = resultSet.getInt("bid");
				String bname = resultSet.getString("bname");
				String image = resultSet.getString("image");
				double price = resultSet.getDouble("price");
				int countbook = resultSet.getInt("countbook");
				
				ShoppingCart items = new ShoppingCart();
				items.setBid(bid);
				items.setBname(bname);
				items.setImage(image);
				items.setPrice(price);
				items.setCountbook(countbook);
				
				itemslist.add(items);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return itemslist;
	}

	@Override
	public void deleteitem(int uid, int bid) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql = "delete from shoppingcart where uid='"+uid+"'and bid='"+bid+"'";
			stmt.executeUpdate(sql);
			C3P0Util.closeConn(conn);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
