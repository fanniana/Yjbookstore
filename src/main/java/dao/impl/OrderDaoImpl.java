package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import dao.OrderDao;
import entity.Book;
import entity.Order;
import entity.OrderDetail;
import entity.User;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<Order> getOrderAll() {
		// TODO Auto-generated method stub
		List<Order> orderlist = new ArrayList<Order>();
		try {			
			Connection conn = C3P0Util.getConn();
			
			Statement stmt=conn.createStatement();
			String sql="select distinct oid from orderdetail";				
			ResultSet resultSet=stmt.executeQuery(sql);
			
			Statement stmt1=conn.createStatement();
			String sql1="select sum(price*orderdetail.count) from orderdetail,book where orderdetail.bid=book.bid group by oid";				
			ResultSet resultSet1=stmt1.executeQuery(sql1);
			
			while (resultSet.next() & resultSet1.next()) {
				
				int oid = resultSet.getInt("oid");
				double totalPrice = resultSet1.getDouble(1);				
				Statement stmt2=conn.createStatement();
				String sql2="select time,state from orderinfo where oid='"+oid+"'";					
				ResultSet resultSet2=stmt2.executeQuery(sql2);
				String time="";
				int state0 = 0;
				if(resultSet2.next()) {
					time = resultSet2.getString("time");
					state0 = resultSet2.getInt("state");
				}
				System.out.print(state0);
				String state = "";
				if(state0 == 0) {
					state = "未付款";
				}else if(state0 == 1) {
					state = "已支付";
				}else if(state0 == 2) {
					state = "已发货";
				}else if(state0 == 3) {
					state = "交易完成";
				}
				
				
				Order order = new Order();
				order.setOid(oid);
				order.setTotalprice(totalPrice);
				order.setTime(time);
				order.setState(state);
				
				orderlist.add(order);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderlist;
	}

	@Override
	public List<Order> getOrderPerson(int uid) {
		// TODO Auto-generated method stub
		List<Order> orderlist = new ArrayList<Order>();
		try {			
			Connection conn = C3P0Util.getConn();
			
			Statement stmt=conn.createStatement();
			String sql="select distinct oid from orderdetail where uid='"+uid+"'";				
			ResultSet resultSet=stmt.executeQuery(sql);
			
			Statement stmt1=conn.createStatement();
			String sql1="select sum(price*orderdetail.count) from orderdetail,book where orderdetail.bid=book.bid and uid='"+uid+"'group by oid";				
			ResultSet resultSet1=stmt1.executeQuery(sql1);
			
			while (resultSet.next() & resultSet1.next()) {
				
				int oid = resultSet.getInt("oid");
				double totalPrice = resultSet1.getDouble(1);				
				Statement stmt2=conn.createStatement();
				String sql2="select time,state from orderinfo where oid='"+oid+"'";					
				ResultSet resultSet2=stmt2.executeQuery(sql2);
				String time="";
				int state0 = 0;
				if(resultSet2.next()) {
					time = resultSet2.getString("time");
					state0 = resultSet2.getInt("state");
				}
				System.out.print(state0);
				String state = "";
				String operate = "";
				if(state0 == 0) {
					state = "未付款";
					operate = "付款";
				}else if(state0 == 1) {
					state = "已支付";
					operate = "催促";
				}else if(state0 == 2) {
					state = "派送中";
					operate = "收货";
				}else if(state0 == 3) {
					state = "已签收";
					operate = "查看详情";
				}
				
				
				Order order = new Order();
				order.setOid(oid);
				order.setTotalprice(totalPrice);
				order.setTime(time);
				order.setState(state);
				order.setOperate(operate);
				
				orderlist.add(order);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderlist;
	}

	@Override
	public int addNewOrder() {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String time=formatter.format(date);  
		System.out.println(time); 
		
		int oid = 0;
		
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql2 = "insert into orderinfo (time,state) values("
					+ "'"+time+"',0)";
			stmt.executeUpdate(sql2);
			Statement stmt1=conn.createStatement();
			String sql="select max(oid) from orderinfo";				
			ResultSet resultSet=stmt1.executeQuery(sql);
			if(resultSet.next()) {
				oid = resultSet.getInt(1);
			}
			C3P0Util.closeConn(conn);
			stmt.close();
			stmt1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oid;
	}

	@Override
	public void addOrderDetail(int oid,int uid,int bid) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			
			Statement stmt1=conn.createStatement();
			String sql="select countbook from shoppingcart where uid='"+uid+"'and bid='"+bid+"'";			
			ResultSet resultSet=stmt1.executeQuery(sql);
			int count = 1;
			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			
			Statement stmt=conn.createStatement();
			String sql2 = "insert into orderdetail(oid,uid,bid,count) values("
					+ "'"+oid+"',"
						+ "'"+uid+"',"
							+ "'"+bid+"',"
								+ "'"+count+"')";
			stmt.executeUpdate(sql2);
			
			C3P0Util.closeConn(conn);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void payMoney(int oid) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			int state = 5;
			String sql = "select state from orderinfo where oid='"+oid+"'";	
			ResultSet resultSet=stmt.executeQuery(sql);
			if(resultSet.next()) {
				state = resultSet.getInt(1);
			}
			if( state == 0 || state == 2) {
				sql = "update orderinfo set state=state+1 where oid='"+oid+"'";
				stmt.executeUpdate(sql);
			}
			C3P0Util.closeConn(conn);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Order> getOrderAllToDo() {
		// TODO Auto-generated method stub
		List<Order> orderlist = new ArrayList<Order>();
		try {			
			Connection conn = C3P0Util.getConn();
			
			Statement stmt=conn.createStatement();
			String sql="select distinct oid from orderdetail";				
			ResultSet resultSet=stmt.executeQuery(sql);
			
			Statement stmt1=conn.createStatement();
			String sql1="select sum(price*orderdetail.count) from orderdetail,book where orderdetail.bid=book.bid group by oid";				
			ResultSet resultSet1=stmt1.executeQuery(sql1);
			
			while (resultSet.next() & resultSet1.next()) {
				
				int oid = resultSet.getInt("oid");
				double totalPrice = resultSet1.getDouble(1);				
				Statement stmt2=conn.createStatement();
				String sql2="select time,state from orderinfo where oid='"+oid+"'";					
				ResultSet resultSet2=stmt2.executeQuery(sql2);
				String time="";
				int state0 = 0;
				if(resultSet2.next()) {
					time = resultSet2.getString("time");
					state0 = resultSet2.getInt("state");
				}
				System.out.print(state0);
				String state = "";
				String operate = "";
				if(state0 == 1) {
					state = "已支付";
					operate = "发货";
				}else {
					continue;
				}
				
				
				Order order = new Order();
				order.setOid(oid);
				order.setTotalprice(totalPrice);
				order.setTime(time);
				order.setState(state);
				order.setOperate(operate);
				
				orderlist.add(order);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderlist;
	}

	@Override
	public void shipped(int oid) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			
			Statement stmt=conn.createStatement();
			String sql = "update orderinfo set state=state+1 where oid='"+oid+"'";
			stmt.executeUpdate(sql);//更改订单状态
			
			String sql1 = "select bid,count from orderdetail where oid='"+oid+"'";
			ResultSet resultSet=stmt.executeQuery(sql1);
			int bid = 0;
			int count = 0;
			while(resultSet.next()){
				bid = resultSet.getInt("bid");
				count = resultSet.getInt("count");
				sql = "update book set count=count-'"+count+"',sellcount=sellcount+'"+count+"' where bid='"+bid+"'";
				stmt.executeUpdate(sql);//更改book中的数据
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order getAOrder(int oid) {
		// TODO Auto-generated method stub
		Order orderinfo=new Order();
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select * from orderinfo where oid='"+oid+"'";			
			ResultSet resultSet=stmt.executeQuery(sql);			
			if (resultSet.next()) {
				int oid1 = resultSet.getInt("oid");
				String time = resultSet.getString("time");
				int state0 = resultSet.getInt("state");
				String state = "";
				
				if(state0 == 0) {
					state = "未付款";
				}else if(state0 == 1) {
					state = "已支付";
				}else if(state0 == 2) {
					state = "已发货";
				}else if(state0 == 3) {
					state = "交易完成";
				}				
				
				orderinfo.setOid(oid1);
				orderinfo.setTime(time);
				orderinfo.setState(state);
				
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return orderinfo;

	}

	@Override
	public User getUserByOid(int oid) {
		// TODO Auto-generated method stub
		User user = new User();
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select uid from orderdetail where oid='"+oid+"'";			
			ResultSet resultSet=stmt.executeQuery(sql);		
			int uid = 2;
			if (resultSet.next()) {
				uid = resultSet.getInt(1);	
			}		
			System.out.println(uid);
			
			sql = "select * from user where uid='"+uid+"'";
			ResultSet resultSet1=stmt.executeQuery(sql);		
			if (resultSet1.next()) {
				
				String username = resultSet1.getString("username");
				int state = resultSet1.getInt("state");
				String tel = resultSet1.getString("tel");
				String address = resultSet1.getString("address");
				
				
				user.setUid(uid);
				user.setUsername(username);
				user.setState(state);
				user.setTel(tel);
				user.setAddress(address);

			}	
			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<OrderDetail> getOrderDetail(int oid) {
		// TODO Auto-generated method stub
		
		List<OrderDetail> orderdetaillist = new ArrayList<OrderDetail>();			
		try {			
			Connection conn = C3P0Util.getConn();
			
			Statement stmt=conn.createStatement();
			String sql="select orderdetail.bid,bname,price,orderdetail.count from orderdetail,book where orderdetail.bid=book.bid and oid='"+oid+"'";				
			ResultSet resultSet=stmt.executeQuery(sql);			
			
			while (resultSet.next()) {
				
				int bid = resultSet.getInt("bid");
				String bname = resultSet.getString("bname");
				double price = resultSet.getDouble("price");
				int count = resultSet.getInt("orderdetail.count");
						
				OrderDetail order = new OrderDetail();
				order.setBid(bid);
				order.setBname(bname);
				order.setPrice(price);
				order.setCount(count);
				
				orderdetaillist.add(order);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderdetaillist;
		
	}
	
	

}
