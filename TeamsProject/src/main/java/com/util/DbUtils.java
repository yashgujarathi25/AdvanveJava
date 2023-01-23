package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

	private static Connection con;
	
	public static void openConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded..!!!");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalab","root","yash@2512");
		System.out.println("Connected To Database...!!!");
	}

	public static Connection getCon() {
		return con;
	}
	
	public static void closeConnection() throws SQLException {
		
		if(con!=null) {
			con.close();
		}
	}
}
