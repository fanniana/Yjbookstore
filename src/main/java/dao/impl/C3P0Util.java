package dao.impl;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util{
	 
    private static ComboPooledDataSource ds=null;
    static{
        ds=new ComboPooledDataSource();
    }


    //获取数据源
    public static DataSource getDataSource(){
        try {
            ds.setDriverClass("com.mysql.cj.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/bookshop?useSSL=false&amp;serverTimezone=UTC");
            ds.setUser("root");
            ds.setPassword("010509");
            ds.setAcquireIncrement(5);
            ds.setInitialPoolSize(5);
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return ds;
    }
  
    //获取一个连接
    public static Connection getConn(){
        Connection conn = null;
        try{
            conn = ds.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 关闭数据库连接
     */
    public static void closeConn(Connection conn){
        try {
            if(conn!=null && conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}