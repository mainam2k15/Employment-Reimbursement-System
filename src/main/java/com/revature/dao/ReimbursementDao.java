package com.revature.dao;

import java.sql.Blob;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public interface ReimbursementDao {

	public void addReimbursementRecord(Reimbursement r, FileItem file);
	
	public List<Reimbursement> getPendingReimbursementById(User curr);
	
	public List<Reimbursement> getResolvedReimbursementById(User curr);
	
	public List<Reimbursement> getAllPendingReimbursement();
	
	public List<Reimbursement> getAllResolvedReimbursement();
	
	public List<Reimbursement> getAllReimbursementById(User curr);
	
	public void approveReimbursementById(Reimbursement r, int resolver_id);
	
	public void denyReimbursementById(Reimbursement r, int resolver_id);
	
	public Blob getBlobById(Reimbursement r);
	
}
