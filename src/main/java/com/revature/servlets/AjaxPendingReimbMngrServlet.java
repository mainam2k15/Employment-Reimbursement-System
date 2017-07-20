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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.PendingReimbursementMngrDto;
import com.revature.pojo.Reimbursement;
import com.revature.service.Service;

@WebServlet("/getPendingReimbMngr")
public class AjaxPendingReimbMngrServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Reimbursement> pending_list = new Service().retrieveAllPendingReimb();
		
		List<PendingReimbursementMngrDto> converted_pending_list = new ArrayList<>();
		for(int i = 0; i < pending_list.size(); i++){
			converted_pending_list.add(new Service().convertToPendingMngr(pending_list.get(i)));
		}
		
		PrintWriter out = resp.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(converted_pending_list);
		resp.setContentType("application/json");
		out.write(json);
	}
	
}
