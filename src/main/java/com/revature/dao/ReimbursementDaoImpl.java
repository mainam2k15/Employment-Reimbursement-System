package com.revature.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public class ReimbursementDaoImpl implements ReimbursementDao{

	private static final String USERNAME = "project1_db";
	private static final String PASSWORD = "p4ssword";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	@Override
	public void addReimbursementRecord(Reimbursement r, FileItem file) {
		String sql = "INSERT INTO reimbursement(r_author, r_status_id, r_type_id, r_amount, r_submitted, r_description, r_receipt) VALUES(?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getAuthor());
			ps.setInt(2, r.getStatus_id());
			ps.setInt(3, r.getType_id());
			ps.setDouble(4, r.getAmount());
			ps.setTimestamp(5, r.getSubmitted());
			ps.setString(6, r.getDescription());
			ps.setBinaryStream(7, file.getInputStream(), (int) file.getSize());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Reimbursement> getPendingReimbursementById(User curr) {
		List<Reimbursement> pending_list = new ArrayList<Reimbursement>();
		Reimbursement currReimb = null;
		String sql = "SELECT * FROM reimbursement r JOIN r_status rs ON r.r_status_id=rs.r_status_id JOIN r_type rt ON r.r_type_id=rt.r_type_id WHERE r.r_status_id = 1 AND r_author = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, curr.getId());
			ResultSet rs = (ResultSet) ps.executeQuery();
			while(rs.next()){
				currReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getTimestamp(7), rs.getTimestamp(8), rs.getString(10));
				currReimb.setType_name(rs.getString(14));
				pending_list.add(currReimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pending_list;
	}

	@Override
	public List<Reimbursement> getResolvedReimbursementById(User curr) {
		List<Reimbursement> resolved_list = new ArrayList<Reimbursement>();
		Reimbursement currReimb = null;
		String sql = "SELECT * FROM reimbursement r JOIN r_status rs ON r.r_status_id=rs.r_status_id JOIN r_type rt ON r.r_type_id=rt.r_type_id JOIN users u ON r.r_resolver=u.u_id WHERE (r.r_status_id = 2 OR r.r_status_id = 3) AND r_author = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, curr.getId());
			ResultSet rs = (ResultSet) ps.executeQuery();
			while(rs.next()){
				currReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getTimestamp(7), rs.getTimestamp(8), rs.getString(10), curr.getF_name() + " " + curr.getL_name(), rs.getString(19) + " " + rs.getString(20), rs.getString(12), rs.getString(14)); 
				resolved_list.add(currReimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resolved_list;
	}

	@Override
	public List<Reimbursement> getAllPendingReimbursement() {
		List<Reimbursement> pending_list = new ArrayList<Reimbursement>();
		Reimbursement currReimb = null;
		String sql = "SELECT * FROM reimbursement r JOIN r_type rt ON r.r_type_id=rt.r_type_id JOIN users u ON r.r_author=u.u_id WHERE r.r_status_id = 1";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			while(rs.next()){
				currReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), -1, rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getTimestamp(7), null, rs.getString(10), rs.getString(17) + " " + rs.getString(18), null, "Pending", rs.getString(12)); 
				pending_list.add(currReimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pending_list;
	}

	@Override
	public List<Reimbursement> getAllResolvedReimbursement() {
		List<Reimbursement> resolved_list = new ArrayList<Reimbursement>();
		Reimbursement currReimb = null;
		String sql = "SELECT * FROM reimbursement r JOIN r_type rt on r.r_type_id=rt.r_type_id JOIN users u ON r.r_author=u.u_id JOIN users ux ON r.r_resolver=ux.u_id JOIN r_status rs ON r.r_status_id=rs.r_status_id WHERE r.r_status_id = 2 OR r.r_status_id = 3";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = (ResultSet) ps.executeQuery();
			while(rs.next()){
				currReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getTimestamp(7), rs.getTimestamp(8), rs.getString(10), rs.getString(17) + " " + rs.getString(18), rs.getString(23) + " " + rs.getString(24), rs.getString(26), rs.getString(12));
				resolved_list.add(currReimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resolved_list;
	}

	@Override
	public List<Reimbursement> getAllReimbursementById(User curr) {
		//need to run 2 SQL statements; one to get those that are resolved; one to get those that are pending
		//cannot match foreign key resolver if it is null [pending]
		List<Reimbursement> reimbursement_list = new ArrayList<Reimbursement>();
		Reimbursement currReimb = null;
		
		//get resolved reimbursements
		String sql = "SELECT * FROM reimbursement r JOIN r_status rs ON r.r_status_id=rs.r_status_id JOIN r_type rt ON r.r_type_id=rt.r_type_id JOIN users u ON u.u_id=r.r_author JOIN users ux ON ux.u_id=r.r_resolver WHERE r_author = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, curr.getId());
			ResultSet rs = (ResultSet) ps.executeQuery();
			while(rs.next()){
				currReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getTimestamp(7), rs.getTimestamp(8), rs.getString(10), rs.getString(19) + " " + rs.getString(20), rs.getString(25) + " " + rs.getString(26), rs.getString(12), rs.getString(14));
				reimbursement_list.add(currReimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//get pending reimbursements
		sql = "SELECT * FROM reimbursement r JOIN r_status rs ON r.r_status_id=rs.r_status_id JOIN r_type rt ON r.r_type_id=rt.r_type_id JOIN users u ON u.u_id=r.r_author WHERE r_author = ? AND r.r_status_id = 1";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, curr.getId());
			ResultSet rs = (ResultSet) ps.executeQuery();
			while(rs.next()){
				currReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getTimestamp(7), rs.getTimestamp(8), rs.getString(10), rs.getString(19) + " " + rs.getString(20), null, rs.getString(12), rs.getString(14));
				reimbursement_list.add(currReimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Collections.sort(reimbursement_list);
		
		return reimbursement_list;
	}

	@Override
	public void approveReimbursementById(Reimbursement r, int resolver_id) {
		
		//need to update status, resolver id, timestamp of resolved
		
		int r_id = r.getR_id();
		String sql = "UPDATE reimbursement SET r_status_id = 2, r_resolver = ?, r_resolved = ? WHERE r_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, resolver_id);
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setInt(3, r_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void denyReimbursementById(Reimbursement r, int resolver_id) {
		
		int r_id = r.getR_id();
		String sql = "UPDATE reimbursement SET r_status_id = 3, r_resolver = ?, r_resolved = ? WHERE r_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, resolver_id);
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setInt(3, r_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Blob getBlobById(Reimbursement r) {
		
		Blob resultBlob = null;
		
		String sql = "SELECT r_receipt FROM reimbursement WHERE r_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getR_id());
			ResultSet rs = ps.executeQuery();
			rs.next();
			resultBlob = rs.getBlob("r_receipt");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
		return resultBlob;
	}

}
