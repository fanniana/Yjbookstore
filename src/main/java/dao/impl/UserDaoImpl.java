package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import dao.UserDao;
import entity.Book;
import entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public int isEmptyUsername(String username) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql1="select count(*) from user where username='"+username+"'";		
			
			ResultSet resultSet1=stmt.executeQuery(sql1);
			int count=0;
			while (resultSet1.next()) {
				count=resultSet1.getInt(1);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet1.close();
			return count;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getPassword(String username) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql2="select password from user where username='"+username+"'";
			ResultSet resultset2=stmt.executeQuery(sql2);
			String password2="";
			if(resultset2.next()) {
				password2=resultset2.getString("password");
			}	
			C3P0Util.closeConn(conn);
			stmt.close();
			resultset2.close();
			return password2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getState(String username) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql3="select state from user where username='"+username+"'";
			ResultSet resultset3=stmt.executeQuery(sql3);
			int state=0;
			if(resultset3.next()) {
				state = resultset3.getInt("state");
			}
			C3P0Util.closeConn(conn);
			stmt.close();
			resultset3.close();
			return state;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void addUser(String username, String password, int state, String tel, String address) {
		// TODO Auto-generated method stub
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql2 = "insert into user(username,password,state,tel,address) values("
					+ "'"+username+"',"
							+ "'"+password+"',"
								+0+","
									+ "'"+tel+"',"
										+ "'"+address+"')";
			stmt.executeUpdate(sql2);
			C3P0Util.closeConn(conn);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		
		List<User> userlist = new ArrayList<User>();
		
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select * from user";				
			ResultSet resultSet=stmt.executeQuery(sql);

			while (resultSet.next()) {
				int uid = resultSet.getInt("uid");
				String username = resultSet.getString("username");
				int state = resultSet.getInt("state");
				String tel = resultSet.getString("tel");
				String address = resultSet.getString("address");
				
				User user=new User();
				user.setUid(uid);
				user.setUsername(username);
				user.setState(state);
				user.setTel(tel);
				user.setAddress(address);
		
				userlist.add(user);
			}			
			C3P0Util.closeConn(conn);
			stmt.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userlist;
	}

	@Override
	public User getSingleUser(String username) {
		// TODO Auto-generated method stub
		User user=new User();
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="select * from user where username='"+username+"'";				
			ResultSet resultSet=stmt.executeQuery(sql);

			while (resultSet.next()) {
				int uid = resultSet.getInt("uid");
				String username1 = resultSet.getString("username");
				int state = resultSet.getInt("state");
				String tel = resultSet.getString("tel");
				String address = resultSet.getString("address");
				
				user.setUid(uid);
				user.setUsername(username1);
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
	public void updateUserInfo(String username, String username1, String tel, String address) {
		
		try {			
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();
			String sql="update user set username='"+username1+"',tel='"+tel+"',address='"+address+"' where username='"+username+"'";				
			stmt.executeUpdate(sql);	
			C3P0Util.closeConn(conn);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean grantPerm(int uid,int state) {
		// TODO Auto-generated method stub
		if(uid==1) {
			return false;
		}
		try {		
			if(state==0) {
				state=1;
			}else {
				state=0;
			}
			
			System.out.println(state);
			Connection conn = C3P0Util.getConn();
			Statement stmt=conn.createStatement();			
			String sql = "update user set state='"+state+"' where uid='"+uid+"'";
			stmt.executeUpdate(sql);
			C3P0Util.closeConn(conn);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
