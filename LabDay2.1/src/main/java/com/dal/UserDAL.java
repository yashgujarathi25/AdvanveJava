package com.dal;

import java.sql.SQLException;

import com.pojo.User;

public interface UserDAL {

	User validateLogin(String email, String password) throws SQLException;
	
	int adduser(String name, String city, String email, String password) throws SQLException;
	
	int updateUser(int id,String name, String city, String email, String password) throws SQLException;
}
