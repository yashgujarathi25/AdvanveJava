package com.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojo.User;
import com.util.DBUtils;

public class UserDALImpl implements UserDAl {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rset;
	public UserDALImpl() throws SQLException {
		con = DBUtils.getCon();
		pstmt=con.prepareStatement("select * from users where email = ?  and  password =?");
		
		
	}
	
	@Override
	public User validateUser(String email, String password) throws SQLException {
		pstmt.setString(1, email);
		pstmt.setString(2, password);
		rset = pstmt.executeQuery();
		User user = null;
		while(rset.next()) {
			user = new User(rset.getInt("userid"), rset.getString("custName"), rset.getString("city"), rset.getString("email"), rset.getString("password"));
		}
		return user;
	}

	
}
