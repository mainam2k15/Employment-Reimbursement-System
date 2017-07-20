package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.PendingReimbursementDto;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.Service;

@WebServlet("/getPendingReimb")
public class AjaxPendingReimb extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User currUser = (User) session.getAttribute("user");
		
		List<Reimbursement> pending_list = new Service().getPendingReimbursement(currUser);
		List<PendingReimbursementDto> converted_pending_list = new ArrayList<>();
		for(int i = 0; i < pending_list.size(); i++){
			converted_pending_list.add(new Service().convertToPendingReimbursement(pending_list.get(i)));
		}

		PrintWriter out = resp.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(converted_pending_list);
		resp.setContentType("application/json");
		out.write(json);
	}
	
}
