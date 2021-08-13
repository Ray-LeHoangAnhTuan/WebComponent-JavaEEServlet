package com.ray.citymanager.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/authorized.do")
public class Authorized extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Authorized() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		boolean authorized = false;
		String uid = null;
		int authLevel = -1;
		
		if (s.getAttribute("userName") != null) 
			uid = (String)s.getAttribute("userName");
		
		if (s.getAttribute("userAuthLevel") != null) 
			authLevel = (int)s.getAttribute("userAuthLevel");
		
		if (authLevel >= 1 && uid !=null && !uid.equals(""))
			authorized = true;
		
		if (!authorized) {
			response.sendRedirect("UserLogin.html");
		} else {
			response.sendRedirect("destinationpage.do");
		}
	}


}
