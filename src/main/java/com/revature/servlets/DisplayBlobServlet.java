package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Reimbursement;
import com.revature.service.Service;

@WebServlet("/displayReceipt")
public class DisplayBlobServlet extends HttpServlet{

	private static final String USERNAME = "project1_db";
	private static final String PASSWORD = "p4ssword";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Blob resultBlob = null;
		int reimb_id = Integer.parseInt(req.getParameter("r_id"));
		
		String sql = "SELECT r_receipt FROM reimbursement WHERE r_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			resultBlob = rs.getBlob("r_receipt");
			
			if(resultBlob == null)
				resp.sendRedirect("homepage");
			
			resp.setContentType("image/jpg");

	        ServletOutputStream out = resp.getOutputStream();
			InputStream in = resultBlob.getBinaryStream();

			int length = (int) resultBlob.length();
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			while((length = in.read(buffer)) != -1){
				out.write(buffer, 0, length);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
}
