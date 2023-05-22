package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void main(String[] args) {
		try {
			Connection conn = C3P0Util.getConn();
			System.out.println(conn);
			Statement stmt=conn.createStatement();
			String sql="SELECT bid FROM orderdetail LIMIT 1";				
			ResultSet resultSet=stmt.executeQuery(sql);
			if(resultSet.next()) {
				System.out.println(resultSet.getInt(1));
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
