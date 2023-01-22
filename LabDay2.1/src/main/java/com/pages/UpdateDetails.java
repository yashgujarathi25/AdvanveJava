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
 * Servlet implementation class UpdateDetails
 */
@WebServlet("/update")
public class UpdateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserDALImpl userDao;
	public void init(ServletConfig config) throws ServletException {
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
		try(PrintWriter pw = response.getWriter()){
			
			response.setContentType("text/html");
			HttpSession hs = request.getSession();
			User userobj = (User)hs.getAttribute("userinfo");
			System.out.println("Session Id = " +hs.getId());
			pw.write("<h1>Update Details Form</h1><hr>");
			pw.write("<form action='updateAction' method='post'>"
					+ "<table>"
					+ "<tr><td>Name :</td><td><input type='text' name='txtnm' placeholder='"+userobj.getCustName()+"'></td></tr>"
					+ "<tr><td>City :</td><td><input type='text' name='txtcity' placeholder='"+userobj.getCity()+"'></td></tr>"
					+ "<tr><td>Email :</td><td><input type='text' name='txtemail' placeholder='"+userobj.getEmail()+"'></td></tr>"
					+ "<tr><td>Password :</td><td><input type='text' name='txtpass' placeholder='"+userobj.getPassword()+"'></td></tr>"
					+ "<tr><td><input type='submit' value='Update'></td></tr>"
					+ "</table>"
					+ "</form>");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
