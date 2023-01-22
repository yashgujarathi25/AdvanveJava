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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	UserDALImpl userDao;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("----inside init login------");
		
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

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("----inside destroy login------");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("----inside doPost login------");
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String email = request.getParameter("txteamil");
		String password = request.getParameter("txtpass");
		
		pw.write("<h1>Welcome Bro : "+email+"</h1>");
		pw.write("<h1>Password : "+password+"</h1>");
		
		try {
			
			User obj = userDao.validateLogin(email, password);
			if(obj!=null) {
				//pw.write("<h1>"+obj+"</h1>");
				HttpSession hs = request.getSession();
				hs.setAttribute("userinfo", obj);
				System.out.println("Session Created with Id : " +hs.getId());
				System.out.println("Session Created on : " +hs.getCreationTime());
				
				response.sendRedirect("welcome");
			}
			else {
				response.sendRedirect("login.html");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
