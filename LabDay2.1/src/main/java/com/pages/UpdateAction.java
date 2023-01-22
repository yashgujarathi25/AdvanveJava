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
 * Servlet implementation class UpdateAction
 */
@WebServlet("/updateAction")
public class UpdateAction extends HttpServlet {
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter pw = response.getWriter()) {
			
			HttpSession hs = request.getSession();
			User userObj = (User)hs.getAttribute("userinfo");
			System.out.println("Sesssion Id = " + hs.getId());
			String name = request.getParameter("txtnm");
			String city = request.getParameter("txtcity");
			String email = request.getParameter("txtemail");
			String pass = request.getParameter("txtpass");
			int i = userDao.updateUser(userObj.getUserid(), name, city, email, pass);
			if(i>0) {
				
				response.sendRedirect("welcome");
			}else {
				pw.write("<h3>Updation Fail</h3>");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
