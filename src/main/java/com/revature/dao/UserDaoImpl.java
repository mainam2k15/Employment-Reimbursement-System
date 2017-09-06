package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.User;

public class UserDaoImpl implements UserDao{

	private static final String USERNAME = "project1_db";
	private static final String PASSWORD = "p4ssword";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public User getUserByEmailPass(User curr){
		User resultUser = null;
		String sql = "SELECT u.u_id, u.u_email, u.u_password, u.u_fname, u.u_lname, r.u_role FROM Users u JOIN u_role r ON u.u_role_id = r.u_role_id WHERE u.u_email = ? AND u.u_password = ?";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, curr.getEmail());
			ps.setString(2,  curr.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				resultUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultUser;		
	}

	@Override
	public void updateUserById(User curr) {
		String sql = "UPDATE Users SET u_email = ?, u_password = ?, u_fname = ?, u_lname = ? WHERE u_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, curr.getEmail());
			ps.setString(2,  curr.getPassword());
			ps.setString(3, curr.getF_name());
			ps.setString(4, curr.getL_name());
			ps.setInt(5, curr.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<User> getAllEmployees() {
		List<User> employeeList = new ArrayList<>();
		User resultUser = null;
		String sql = "SELECT * FROM users WHERE u_role_id = 1";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				resultUser = new User();
				resultUser.setId(rs.getInt(1));
				resultUser.setEmail(rs.getString(3));
				resultUser.setF_name(rs.getString(5));
				resultUser.setL_name(rs.getString(6));
				employeeList.add(resultUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return employeeList;
	}
}
