package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.User;
import com.revature.service.Service;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
		session.removeAttribute("err");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User clientUser = new User(username, password);
		
		clientUser = new Service().authenticateUser(clientUser);
		
		HttpSession session = req.getSession();
		
		if(clientUser != null){
			session.setAttribute("user", clientUser);
			if(clientUser.getRole().equals("Employee"))
				resp.sendRedirect("homepage");
			else
				resp.sendRedirect("managerHomepage.html");
		} else{
			session.setAttribute("err", "Incorrect Login Credentials");
			resp.sendRedirect("login");
		}
	}
	
}
