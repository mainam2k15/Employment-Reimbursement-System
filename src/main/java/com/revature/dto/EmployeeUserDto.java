package com.revature.dto;

public class EmployeeUserDto {

	private int user_id;
	private String email;
	private String fName;
	private String lName;
	
	public EmployeeUserDto(){
		
	}

	public EmployeeUserDto(int user_id, String email, String fName, String lName) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.fName = fName;
		this.lName = lName;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	@Override
	public String toString() {
		return "EmployeeUserDto [user_id=" + user_id + ", email=" + email + ", fName=" + fName + ", lName=" + lName
				+ "]";
	}
	
}
