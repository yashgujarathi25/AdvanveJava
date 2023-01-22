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

import com.dal.UserDALImpl;
import com.pojo.User;
import com.util.DBUtils;

/**
 * Servlet implementation class ValidateServlet
 */
@WebServlet(value ="/validateServlet", loadOnStartup = 1)
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDALImpl userDao;
	public void init(ServletConfig config) throws ServletException {
		System.out.println("-----inside init method----");
		try {
			
			DBUtils.openConnection();
			userDao = new UserDALImpl();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void destroy() {
		System.out.println("----inside destroy method------");
		
		try {
			
			DBUtils.closeConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()) {
			
			String email = request.getParameter("txtemail");
			String pass = request.getParameter("txtpass");
//			
//			if(email.equals("yash@gmail.com") && pass.equals("yash@123")) {
//				pw.write("<h1>Welcome : "+email+"</h1>");
//							
//			}
//			else {
//				pw.write("Invalid User");
//			}
			
			
			User user = userDao.validateUser(email, pass);
			if(user!=null) {
				pw.write("<h1>Welcome : "+user.getCustName()+"</h1>");
				pw.write("<h3>"+user+"</h3>");
			}else {
				pw.write("Invalid User");
			}
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
