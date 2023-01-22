package com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojo.User;
import com.util.DBUtils;

public class UserDALImpl implements UserDAL{
	
	private Connection con;
	private PreparedStatement pstmt,pstmt1,pstmt2;
	private ResultSet rset;
	public UserDALImpl() throws SQLException {
		con=DBUtils.getCon();
		pstmt=con.prepareStatement("select * from users where email =? and password =?");
		pstmt1=con.prepareStatement("insert into users(custName, city,email,password) values(?,?,?,?)");
		pstmt2 = con.prepareStatement("update users set custName=?,city=?,email=?,password=? where userid=?");
	}
		
	@Override
	public User validateLogin(String email, String pass) throws SQLException {
		pstmt.setString(1, email);
		pstmt.setString(2, pass);
		rset = pstmt.executeQuery();
		User obj = null;
		
		while(rset.next()) {
			obj = new User(rset.getInt("userid"), rset.getString("custName"), 
					rset.getString("city"), 
					rset.getString("email"), rset.getString("password"));
		}
		return obj;
	}

	@Override
	public int adduser(String name, String city, String email, String password) throws SQLException {
		pstmt1.setString(1, name);
		pstmt1.setString(2, city);
		pstmt1.setString(3, email);
		pstmt1.setString(4, password);
		
		int i = pstmt1.executeUpdate();
		return i;
	}

	@Override
	public int updateUser(int id,String name, String city, String email, String password) throws SQLException {
		
		pstmt2.setString(1, name);
		pstmt2.setString(2, city);
		pstmt2.setString(3, email);
		pstmt2.setString(4, password);
		pstmt2.setInt(5, id);
		
		int i = pstmt2.executeUpdate();
		
		return i;
	}

	
}
