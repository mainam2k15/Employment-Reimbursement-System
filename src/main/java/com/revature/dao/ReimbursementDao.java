package com.revature.dao;

import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public interface ReimbursementDao {

	public void addReimbursementRecord(Reimbursement r);
	
	public List<Reimbursement> getPendingReimbursementById(User curr);
	
	public List<Reimbursement> getResolvedReimbursementById(User curr);
	
	public List<Reimbursement> getAllPendingReimbursement();
	
	public List<Reimbursement> getAllResolvedReimbursement();
	
	public List<Reimbursement> getAllReimbursementById(User curr);
	
	public void approveReimbursementById(Reimbursement r, int resolver_id);
	
	public void denyReimbursementById(Reimbursement r, int resolver_id);
	
}
