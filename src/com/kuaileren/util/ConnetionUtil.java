package com.kuaileren.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnetionUtil {
	static String user = "root";
	static String password = "root";
	static String url = "jdbc:mysql://localhost:3306/gou60";
	static String driver = "com.mysql.jdbc.Driver";
	
	
	public static Connection getConnetion(){
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	public static void close(Connection conn){
		try {
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void closeStatement(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()){
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
