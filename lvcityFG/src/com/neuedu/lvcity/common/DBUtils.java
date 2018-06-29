package com.neuedu.lvcity.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 鏁版嵁搴撳伐鍏风被
 */
public class DBUtils {

	/**
	 * 鑾峰彇鏁版嵁搴撹繛鎺�
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/lvcitydb";
			String user = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Can not get connection", e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Can not get connection", e);
		}
		
		/*	         1.鍔犺浇jdbc椹卞姩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.瀹氫箟杩炴帴url
		String url = "jdbc:oracle:thin:@localhost:1521:neuedu";
		//3.鑾峰彇鏁版嵁搴撹繛鎺�    
		Connection conn = DriverManager.getConnection(url,"scott","tiger");*/
		
		/*try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context
					.lookup("java:comp/env/jdbc/lvcity");
			conn = ds.getConnection();
			System.out.println("connect sucess!");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Can not get connection", e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServiceException("Can not get connection", e);
		}
*/
		return conn;
	}

	/**
	 * 寮�鍚簨鍔�
	 * 
	 * @param conn
	 */
	public static void beginTransaction(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new ServiceException("Can not begin transaction", e);
		}
	}

	/**
	 * 鎻愪氦浜嬪姟
	 * 
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new ServiceException("Can not commit transaction", e);
		}
	}

	/**
	 * 鍥炴粴浜嬪姟
	 * 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new ServiceException("Can not rollback transaction", e);
		}
	}

	/**
	 * 鍏抽棴杩炴帴
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new ServiceException("Can not close connection", e);
		}
	}

	/**
	 * 鍏抽棴statement
	 * 
	 * @param stmt
	 */
	public static void closeStatement(ResultSet rs, Statement stmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new ServiceException("Can not close statement", e);
		}
	}
}
