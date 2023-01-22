package com.dal;

import java.sql.SQLException;

import com.pojo.User;

public interface UserDAl {

	User validateUser(String email, String password) throws SQLException;
}
