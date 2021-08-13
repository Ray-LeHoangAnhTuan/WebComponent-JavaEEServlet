package com.ray.citymanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.citymanager.models.DBManager;
import com.ray.citymanager.models.IConnectionBehavior;
import com.ray.citymanager.models.MySQLConnectionBehavior;


@WebServlet("/ListAllCities")
public class ListAllCities extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ListAllCities() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder("<html><body>");
		
		String uid = getServletContext().getInitParameter("dbuserid");
		String pwd = getServletContext().getInitParameter("dbuserpwd");
		String cat = getServletContext().getInitParameter("dbinitcat");
		
		// Option 1: Connect to MySQL
		IConnectionBehavior icb = new MySQLConnectionBehavior(uid,pwd,cat);
		
		// Option 2: Connect to MS SQLServer
		// IConnectionBehavior icb = new MssqlConnectionBehavior(uid,pwd,cat);
		System.out.println(icb.getConnectionDetails());
		System.out.println(icb.getConnectionURL());
		
		DBManager dbm = new DBManager(icb);
		
		try {
			if (!dbm.isConnected())
			{
				if (!dbm.openConnection())
					sb.append("Could not connect to Database");
			}
			
			sb.append("<table border=1>"
					+ "<tr><td>Id</td><td>NAME</td><td>COUNTRY_CODE</td><td>COUNTRY</td><td>Population</td></tr>");
			String query = "select * from city order by population DESC";
			
			ResultSet rs = dbm.ExecuteResultSet(query);
			
			while (rs.next())
			{
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String countryCode = rs.getString("CountryCode");
				String country = rs.getString("Country");
				int pop = rs.getInt("Population");
				
				sb.append("<tr><td>" + id + "</td>"
						+ "<td>" + name + "</td>"
						+ "<td>" + countryCode + "</td>"
						+ "<td>" + country + "</td>"
						+ "<td>" + pop + "</td></tr>");
			}
			sb.append("</table>");
		}
		catch (Exception ex)
		{
			sb.append("<h1>Error:" + ex.getMessage() + "</h1>");
		}
		sb.append("</body></html>");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
	}


}
