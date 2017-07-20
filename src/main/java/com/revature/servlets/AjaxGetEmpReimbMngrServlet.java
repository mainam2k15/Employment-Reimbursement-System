package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.ResolvedReimbursementMngrDto;
import com.revature.pojo.Employee;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.Service;

@WebServlet("/getEmpReimb")
public class AjaxGetEmpReimbMngrServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> myMap = req.getParameterMap();
		
		Set<String> mySet =  myMap.keySet();
		
		ObjectMapper jackson = new ObjectMapper();
		
		Object obj = mySet.toArray()[0];
		
		Employee empName = (Employee) jackson.readValue(((String)obj), Employee.class);
		System.out.println(empName);
		
		//employee object now has the name from the select box
		//get all reimbursements from this employee
		
		//get id of employee
		User employee = new User();
		List<User> employee_list = new Service().retrieveAllEmployees();
		for(int i = 0; i < employee_list.size(); i++){
			String name = employee_list.get(i).getF_name() + " " + employee_list.get(i).getL_name();
			if(empName.getEmployeeName().equals(name))
				employee = employee_list.get(i);
		}
		
		List<Reimbursement> emp_reimb_list = new Service().retrieveAllEmpReimb(employee);
		
		//convert to DTO
		List<ResolvedReimbursementMngrDto> converted_emp_reimb_list = new ArrayList<>();
		for(int i = 0; i < emp_reimb_list.size(); i++){
			converted_emp_reimb_list.add(new Service().convertToResolvedMngr(emp_reimb_list.get(i)));
		}
				
		PrintWriter out = resp.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(converted_emp_reimb_list);
		resp.setContentType("application/json");
		out.write(json);
		
		
	}
	
}
