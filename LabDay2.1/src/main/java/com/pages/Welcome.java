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

import com.dal.UserDALImpl;
import com.pojo.User;
import com.util.DBUtils;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserDALImpl userDao;
    
	public void init(ServletConfig config) throws ServletException {
		System.out.println("----Inside init of welcome----");
		try {
			DBUtils.openConnection();
			userDao = new UserDALImpl();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void destroy() {
		try {
			DBUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter pw = response.getWriter()) {
			
			response.setContentType("text/html");	
			
			HttpSession hs = request.getSession();
			System.out.println("Session Created with Id : " +hs.getId());
			System.out.println("Session Created on : " +hs.getCreationTime());
			User userObj = (User)hs.getAttribute("userinfo");
			System.out.println(userObj);
			pw.write("<h1>Welcome "+userObj.getCustName()+"....!!!!</h1>");
			pw.write("<h3>"+userObj+"</h3>");
			
			pw.write("<a href='update'><button type='button' name='btn'>Update Details</button></a>");
			pw.write("&nbsp<a href='delete'><button type='button' name='btn'>Delete</button></a>");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
