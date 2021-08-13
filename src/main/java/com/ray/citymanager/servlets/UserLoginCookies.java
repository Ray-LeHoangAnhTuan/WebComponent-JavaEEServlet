package com.ray.citymanager.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLoginCookies
 */
@WebServlet("/userlogincookies.do")
public class UserLoginCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLoginCookies() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("userID");
		String pwd = request.getParameter("pwd");
		boolean remember = false;
		if (request.getParameter("remember") != null) {
			String rememberMe = request.getParameter("remember");
			if (rememberMe.equalsIgnoreCase("on"))
				remember = true;
		}
		
		int authLevel = 1;
		
		HttpSession s = request.getSession();
		s.setAttribute("sessionAtUserlogin.do", s.isNew());
		s.setAttribute("userName", uid);
		s.setAttribute("userAuthLevel", authLevel);
		
		if (remember) 
		{
			int cookieLife = 3600*24*7; // 7 days
			
			Cookie uidCook = new Cookie("credentials_uid", uid);
			Cookie pwdCook = new Cookie("credentials_pwd", pwd);
			
			uidCook.setMaxAge(cookieLife);
			pwdCook.setMaxAge(cookieLife);
			
			response.addCookie(uidCook);
			response.addCookie(pwdCook);
		}
		
		if (authLevel < 1 || uid == null || uid =="") {
			response.sendRedirect(response.encodeRedirectUrl("index_cookies.html"));
		} else {
			response.sendRedirect(response.encodeRedirectUrl("destinationpage.do"));
		}
	}

}
