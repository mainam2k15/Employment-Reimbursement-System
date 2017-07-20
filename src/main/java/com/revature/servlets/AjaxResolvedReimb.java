package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dto.ResolvedReimbursementDto;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.Service;

@WebServlet("/getResolvedReimb")
public class AjaxResolvedReimb extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User currUser = (User) session.getAttribute("user");

		List<Reimbursement> resolved_list = new Service().getResolvedReimbursement(currUser);
		List<ResolvedReimbursementDto> converted_resolved_list = new LinkedList<>();
		for(int i = 0; i < resolved_list.size(); i++){
			converted_resolved_list.add(new Service().convertToResolvedReimbursement(resolved_list.get(i)));
		}

		PrintWriter out = resp.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(converted_resolved_list);
		resp.setContentType("application/json");
		out.write(json);
	}
}
