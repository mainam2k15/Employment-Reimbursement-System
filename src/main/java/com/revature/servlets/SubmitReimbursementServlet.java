package com.revature.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.service.Service;

@WebServlet("/submitReimbursement")
public class SubmitReimbursementServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Cache-Control", "no-store");
		
		HttpSession session = req.getSession();
		
		if(session.getAttribute("user") == null){
			System.out.println("Unauthorized access");
			resp.sendRedirect("login");
			return;
		} else{
			req.getRequestDispatcher("WEB-INF/submitReimbursement.jsp").forward(req, resp);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//values in reimbursement_type table
		final int LODGING = 1;
		final int TRAINING = 2;
		final int TRAVEL = 3;
		final int SUPPLIES = 4;
		final int FOOD = 5;
		
		try{

			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(factory);

			
			List items = sfu.parseRequest(req);
			FileItem file = (FileItem) items.get(3);
			
			String r_type = ((FileItem) items.get(0)).getString();
			double amnt = Double.parseDouble(((FileItem) items.get(1)).getString());
			String description = ((FileItem) items.get(2)).getString();
			System.out.println(description);
			
			int type_id = 0;
			switch(r_type){
				case "Lodging":
					type_id = LODGING;
					break;
				case "Training":
					type_id = TRAINING;
					break;
				case "Travel":
					type_id = TRAVEL;
					break;
				case "Supplies":
					type_id = SUPPLIES;
					break;
				case "Food":
					type_id = FOOD;
					break;
			}
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			HttpSession session = req.getSession();
			User currUser = (User) session.getAttribute("user");
			int author = currUser.getId();
						
			Reimbursement r = new Reimbursement(author, 1, type_id, amnt, timestamp, description);
			new Service().addReimbursement(r, file);
			
			req.getRequestDispatcher("WEB-INF/submitReimbursement.jsp").forward(req, resp);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
