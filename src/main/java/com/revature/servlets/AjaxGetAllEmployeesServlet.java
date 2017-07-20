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
import com.revature.dto.EmployeeUserDto;
import com.revature.pojo.User;
import com.revature.service.Service;

@WebServlet("/getAllEmployees")
public class AjaxGetAllEmployeesServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<User> employee_list = new Service().retrieveAllEmployees();
		
		List<EmployeeUserDto> converted_employee_list = new ArrayList<>();
		for(int i = 0; i < employee_list.size(); i++){
			converted_employee_list.add(new Service().convertToEmployee(employee_list.get(i)));
		}
		
		PrintWriter out = resp.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(converted_employee_list);
		resp.setContentType("application/json");
		out.write(json);
	
	}
	
}
