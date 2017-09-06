package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.User;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Cache-Control", "no-store");
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("user") == null){
			System.out.println("Unauthorized access");
			resp.sendRedirect("login");
			return;
		} else{
			//check role here
			User currUser = (User) session.getAttribute("user");
			if(currUser.getRole().equals("Employee"))
				req.getRequestDispatcher("WEB-INF/employeeHomepage.jsp").forward(req, resp);
			else
				req.getRequestDispatcher("WEB-INF/managerHomepage.jsp").forward(req, resp);
		}
	}
}
