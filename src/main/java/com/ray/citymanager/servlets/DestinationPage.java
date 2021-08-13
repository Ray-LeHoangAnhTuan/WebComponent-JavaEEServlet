package com.ray.citymanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DestinationPage
 */
@WebServlet("/destinationpage.do")
public class DestinationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DestinationPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		boolean sessionAtUserlogin = (boolean)s.getAttribute("sessionAtUserlogin.do");
		
		if (s !=null && !s.isNew() && s.getAttribute("userName") != null)
		{
			String uid = (String)s.getAttribute("userName");
			out.println(String.format("<html><body><h1>Check session.IsNew after set username & pass is: %b </h1><br>"
					+ "<h3>Check session.IsNew <b>before</b> set username & pass is:%b</h3></body></html>", s.isNew(), sessionAtUserlogin));
		}
		else {
			out.println("<html><body><h1>Unauthorized</h1></body></html>");
		}
	}


}
