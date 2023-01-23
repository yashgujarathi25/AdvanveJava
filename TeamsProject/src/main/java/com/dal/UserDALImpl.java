package com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojo.User;
import com.util.DbUtils;

public class UserDALImpl implements UserDAL {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rset;
	public UserDALImpl() throws SQLException {
		
		con = DbUtils.getCon();
		//select * from users where email=? and password=?
		pstmt = con.prepareStatement("select * from users where email=? and password=?");
	}
	
	
	
	
	@Override
	public User validateUser(String email, String password) throws SQLException {
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		rset = pstmt.executeQuery();
		User userObj = null;
		while(rset.next())
		{
			userObj = new User(rset.getInt("userid"), rset.getString("custName"), rset.getString("city"), rset.getString("email"), rset.getString("password"));
		}
		return userObj;
	}

}
