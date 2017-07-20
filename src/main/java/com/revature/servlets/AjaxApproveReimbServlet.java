package com.revature.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Index;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.Service;

@WebServlet("/approveReimb")
public class AjaxApproveReimbServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> myMap = req.getParameterMap();
		
		Set<String> mySet =  myMap.keySet();
		
		ObjectMapper jackson = new ObjectMapper();
		
		Object obj = mySet.toArray()[0];
		
		System.out.println(obj);
		
		Index in = (Index) jackson.readValue(((String)obj), Index.class);
		System.out.println("Checked Indexes: " + in);
		
		//need to get pending list and match indexes
		List<Reimbursement> pending_list = new Service().retrieveAllPendingReimb();
		
		HttpSession session = req.getSession();
		User mngr = (User) session.getAttribute("user");
		int resolver_id = mngr.getId();
		
		//update info of appr indexes to approved
		int[] checkedIndexes = in.getIndex();
		for(int i = 0; i < checkedIndexes.length; i++){
			new Service().approveReimb(pending_list.get(checkedIndexes[i]), resolver_id);
		}
	
	}
	
}
