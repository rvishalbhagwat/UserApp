package com.vishal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Connection conn;
	public Statement st;

	@Override
	public void init() throws ServletException {

		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("uName");
		String fName = request.getParameter("fName");
		String lNane = request.getParameter("lName");
		String email = request.getParameter("email");
		int age = Integer.valueOf(request.getParameter("age"));
		int phoneNo = Integer.valueOf(request.getParameter("phNo"));
		String query = "insert into UserDB values('" + username + "','" + fName + "','" + lNane + "','" + email + "','"
				+ age + "','" + phoneNo + "') ";
		try {
			st = conn.createStatement();
			int n = st.executeUpdate(query);
			PrintWriter out = response.getWriter();
			if (n > 0)
				out.print(n + "congrats! user creation sucessful");
			else
				out.print("sorry ,user creation failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void destroy() {
		try {
			conn.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
