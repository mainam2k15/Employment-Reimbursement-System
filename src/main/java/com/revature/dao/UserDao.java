package com.revature.dao;

import java.util.List;

import com.revature.pojo.User;

public interface UserDao {

	public User getUserByEmailPass(User curr);
	
	public void updateUserById(User curr);
	
	public List<User> getAllEmployees();
	
}
