package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.Service;
import com.revature.pojo.User;

@WebServlet("/viewInfo")
public class ViewInfoServlet extends HttpServlet{

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
			req.getRequestDispatcher("WEB-INF/viewInfo.jsp").forward(req, resp);
			session.removeAttribute("updateErr");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		User currUser = (User) session.getAttribute("user");
		
		int id = currUser.getId();
		
		String email = req.getParameter("email");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String password = req.getParameter("password");
		
		//validate email is in correct form
		boolean correctEmail = new Service().validateEmail(email);
		if(correctEmail == false && !email.isEmpty()){
			session.setAttribute("updateErr", "Invalid email pattern");
			resp.sendRedirect("viewInfo");
//			req.getRequestDispatcher("viewinfo").forward(req, resp);
			return;
		}
		
		
		if(email.isEmpty()) email = currUser.getEmail();
		if(fname.isEmpty()) fname = currUser.getF_name();
		if(lname.isEmpty()) lname = currUser.getL_name();
		if(password.isEmpty()) password = currUser.getPassword();
		
		User updatedUser = new User(id, email, password, fname, lname);
		if(updatedUser.equals(currUser)){
			resp.sendRedirect("viewInfo");
			return;
		}
		
		updatedUser = new Service().updateUser(updatedUser);
		session.setAttribute("user", updatedUser);
		session.setAttribute("successMsg", "User successfully updated!");
		resp.sendRedirect("viewInfo");
	}
	
}
