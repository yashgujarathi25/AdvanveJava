package com.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dal.UserDALImpl;
import com.pojo.User;
import com.util.DbUtils;
//key=validateServlet
//value=com.pages.LoginServlet
@WebServlet(value="/validateServlet", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDALImpl userDao;
	public void init(ServletConfig config) throws ServletException {
		try {
			System.out.println("-----inside init method-----");
			DbUtils.openConnection();
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
			DbUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter();){
			
			//response.getWriter().write("<h1>Hello<h1>");
			String email = request.getParameter("txtemail");
			String pass = request.getParameter("txtpass");
			User userObj = userDao.validateUser(email, pass);
			
			if(userObj!=null) {
//				pw.write("<h1>Welcome...!!!!</h1>");
//				pw.write("<h3>"+userObj+"</h3>");
				
				HttpSession hs = request.getSession();
				System.out.println("Session id = "+hs.getId());
				hs.setAttribute("user_info",userObj);
				
				response.sendRedirect("welcome");
//				RequestDispatcher rd = request.getRequestDispatcher("welcome");
//				rd.forward(request, response);
				
				
			}else {
				pw.write("invalid User");
				response.sendRedirect("login.html");
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}

}
