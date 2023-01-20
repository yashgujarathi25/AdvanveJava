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
import com.util.DBUtils;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
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

	/**
	 * @see Servlet#destroy()
	 */
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw= response.getWriter();
		response.setContentType("text/html");
		String name = request.getParameter("txtname");
		String city = request.getParameter("txtcity");
		String email = request.getParameter("txtemail");
		String pass = request.getParameter("txtpass");
		
		try {
			int i = userDao.adduser(name, city, email, pass);
			
			if(i>0) {
				pw.write("<h2>User Registered Successfully...!!!!</h2>");
			}
			else {
				pw.write("<h2>Unsuccessfull Registration..!!!</h2>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
