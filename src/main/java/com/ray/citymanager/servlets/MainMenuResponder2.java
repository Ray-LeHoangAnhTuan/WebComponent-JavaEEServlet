package com.ray.citymanager.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuResponder2
 */
@WebServlet("/mainmenuresponder2.do")
public class MainMenuResponder2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public MainMenuResponder2() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userChoice = request.getParameter("menuChoice");
		request.setAttribute("userChoice", userChoice);
		
		String[] userOptions = request.getParameterValues("adminoptions");
		if (userOptions != null) 
		{
			for (int i=0; i<userOptions.length; i++) 
			{
				if (userOptions[i].equalsIgnoreCase("useDB"))
					request.setAttribute("useDB", "1");
				if (userOptions[i].equalsIgnoreCase("sendEmail"))
					request.setAttribute("sendEmail", "1");
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mainmenuresponderhelper.do");
		dispatcher.forward(request, response);
	}

}
