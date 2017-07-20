package com.revature.pojo;

public class Employee {

	private String employeeName;
	
	public Employee(){
		
	}

	public Employee(String employeeName) {
		super();
		this.employeeName = employeeName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Override
	public String toString() {
		return "Employee [employeeName=" + employeeName + "]";
	}
	
}
