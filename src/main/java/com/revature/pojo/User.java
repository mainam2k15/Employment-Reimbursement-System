package com.revature.pojo;

public class User {

	private int id;
	private String email;
	private String password;
	private String f_name;
	private String l_name;
	private String role;
	
	public User(){
		
	}

	public User(String email, String password){
		super();
		this.email = email;
		this.password = password;
	}
	
	public User(int id, String email, String password, String f_name, String l_name, String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.f_name = f_name;
		this.l_name = l_name;
		this.role = role;
	}
	
	public User(int id, String email, String password, String f_name, String l_name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.f_name = f_name;
		this.l_name = l_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", f_name=" + f_name + ", l_name="
				+ l_name + ", role=" + role + "]";
	}
}
