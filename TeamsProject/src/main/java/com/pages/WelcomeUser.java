package com.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dal.UserDAL;
import com.dal.UserDALImpl;
import com.pojo.User;
import com.util.DbUtils;

/**
 * Servlet implementation class WelcomeUser
 */
@WebServlet("/welcome")
public class WelcomeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDALImpl userDao;
	public void init(ServletConfig config) throws ServletException {
		try {
			DbUtils.openConnection();
			userDao = new UserDALImpl();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void destroy() {
		try {
			DbUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try(PrintWriter pw = response.getWriter()){
			HttpSession hs = request.getSession();
			System.out.println("Session id for welcome : "+hs.getId());
			User userObj = (User)hs.getAttribute("user_info");
			
			pw.write("<h1>Welcome : "+userObj+"</h1>");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
				
				
				
		
	}

}
