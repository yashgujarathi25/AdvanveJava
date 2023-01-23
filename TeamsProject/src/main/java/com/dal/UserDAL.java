package com.dal;

import java.sql.SQLException;

import com.pojo.User;

public interface UserDAL {

	User validateUser(String email, String password) throws SQLException;
}
