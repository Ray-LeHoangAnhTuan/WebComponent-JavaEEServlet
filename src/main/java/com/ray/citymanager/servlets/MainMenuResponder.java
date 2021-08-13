package com.ray.citymanager.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuResponder
 */
@WebServlet("/mainmenuresponder.do")
public class MainMenuResponder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MainMenuResponder() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
		String userChoice = request.getParameter("menuChoice");
		String[] userOptions = request.getParameterValues("adminoptions");
		
		StringBuilder params = new StringBuilder("");
		String queryStringParams = "";
		
		if (userOptions != null)
		{
			boolean isFirst = true;
			for (int i=0; i<userOptions.length; i++)
			{
				if (isFirst)
				{
					params.append("?");
				}
				else 
				{
					params.append("&");
				}
				
				if (userOptions[i].equalsIgnoreCase("useDB"))
				{
					params.append("userDB=1");
				}
				else if (userOptions[i].equalsIgnoreCase("sendEmail"))
				{
					params.append("sendEmail=1");
				}
				
				isFirst = false;
			}
			queryStringParams = params.toString();
			
			
		}
		

		if (userChoice.equals("1"))
		{
			response.sendRedirect("ListCities.html" + queryStringParams);
		} 
		else if (userChoice.equals("2"))
		{
			response.sendRedirect("AddCity.html" + queryStringParams);
		} 
		else if (userChoice.equals("3"))
		{
			response.sendRedirect("DeleteCity.html" + queryStringParams);
		} 
		else 
		{
			response.sendRedirect("index.html");
		}
	}

}
