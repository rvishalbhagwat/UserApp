package com.vishal.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ListUsers")
public class ListUsers extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Connection conn;
	public Statement st;

	public void init(ServletConfig config) throws ServletException {

		try {
		    ServletContext context= config.getServletContext();
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			conn = DriverManager.getConnection(context.getInitParameter("url"),context.getInitParameter("user"),context.getInitParameter("password"));

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
		String dbName = request.getParameter("uName");
		String query ="SELECT * from userdb";
		try {
			st = conn.createStatement();
			ResultSet rs= st.executeQuery(query);
			PrintWriter out = response.getWriter();
			while(rs.next())
				out.println((rs.getString("username"))+" "+rs.getString("firstname")+" "+rs.getString("lastname")+" "+rs.getString("email")+" "+rs.getInt("age")+" "+rs.getInt("phoneno"));
			    out.print("<br>");
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
