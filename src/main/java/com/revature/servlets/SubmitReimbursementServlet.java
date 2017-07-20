package com.revature.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.Service;

@WebServlet("/submitReimbursement")
public class SubmitReimbursementServlet extends HttpServlet{

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
			req.getRequestDispatcher("WEB-INF/submitReimbursement.jsp").forward(req, resp);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String r_type = req.getParameter("r_type");
		double amnt = Double.parseDouble(req.getParameter("amount"));
		String description = req.getParameter("description");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		HttpSession session = req.getSession();
		User currUser = (User) session.getAttribute("user");
		int author = currUser.getId();
		
		//want to get the r_id from the type
		
		Reimbursement r = new Reimbursement(currUser.getId(), 1, 1, amnt, timestamp, description);
		new Service().addReimbursement(r);
		
		req.getRequestDispatcher("WEB-INF/submitReimbursement.jsp").forward(req, resp);
	}
	
}
