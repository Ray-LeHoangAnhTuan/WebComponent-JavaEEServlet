package com.ray.citymanager.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadSafeGetSeed
 */
@WebServlet("/threadsafegetseed.do")
public class ThreadSafeGetSeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ThreadSafeGetSeed() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doThreadSafe = request.getParameter("threadsafetyselection");
		
		int initialSeed = Integer.parseInt(getServletContext().getInitParameter("initialSeed"));
		
		getServletContext().setAttribute("currentSeedValue", initialSeed);
		
		if (doThreadSafe.equalsIgnoreCase("nonthreadsafe"))
		{
			response.sendRedirect("nonthreadsafeservlet.do");
		} 
		else if (doThreadSafe.equalsIgnoreCase("threadsafe"))
		{
			response.sendRedirect("threadsafeservlet.do");
		}
		else if (doThreadSafe.equalsIgnoreCase("requestthread"))
		{
			response.sendRedirect("threadsaferequest.do");
		}
		else {
			response.sendRedirect("index3.html");
		}
	}

}
