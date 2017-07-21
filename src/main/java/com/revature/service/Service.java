package com.revature.service;

import java.sql.Blob;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.FileItem;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.dto.EmployeeUserDto;
import com.revature.dto.PendingReimbursementDto;
import com.revature.dto.PendingReimbursementMngrDto;
import com.revature.dto.ResolvedReimbursementDto;
import com.revature.dto.ResolvedReimbursementMngrDto;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public class Service {

	public User authenticateUser(User client) {
		UserDao userDao = new UserDaoImpl();
		User x = userDao.getUserByEmailPass(client);
		return x;
	}

	public User updateUser(User client) {
		UserDao userDao = new UserDaoImpl();
		userDao.updateUserById(client);
		User updatedUser = userDao.getUserByEmailPass(client);
		return updatedUser;
	}

	// ensure email meets pattern
	public boolean validateEmail(String email) {
		Pattern ptr = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = ptr.matcher(email);
		return matcher.find();
	}

	public void addReimbursement(Reimbursement rec, FileItem file){
		ReimbursementDao reimbDao = new ReimbursementDaoImpl();
		reimbDao.addReimbursementRecord(rec, file);
	}
	
	public PendingReimbursementDto convertToPendingReimbursement(Reimbursement r) {
		PendingReimbursementDto reimb = new PendingReimbursementDto(r.getR_id(), r.getType_name(), r.getAmount(), r.getSubmitted().toGMTString(), r.getDescription());
		return reimb;
	}
	
	public ResolvedReimbursementDto convertToResolvedReimbursement(Reimbursement r) {
		ResolvedReimbursementDto reimb = new ResolvedReimbursementDto(r.getR_id(), r.getType_name(), r.getAmount(), r.getSubmitted().toGMTString(), r.getDescription(), r.getResolver_name(), r.getStatus_name(), r.getResolved().toGMTString());
		return reimb;
	}
	
	public PendingReimbursementMngrDto convertToPendingMngr(Reimbursement r) {
		PendingReimbursementMngrDto reimb = new PendingReimbursementMngrDto(r.getR_id(), r.getAuthor_name(), r.getType_name(), r.getAmount(), r.getSubmitted().toGMTString(), r.getDescription());
		return reimb;
	}
	
	public ResolvedReimbursementMngrDto convertToResolvedMngr(Reimbursement r) {
		String resolvedTimestamp;
		if(r.getResolved() == null)
			resolvedTimestamp = "";
		else
			resolvedTimestamp = r.getResolved().toGMTString();
		
		String resolverName;
		if(r.getResolver_name() == null)
			resolverName = "";
		else
			resolverName = r.getResolver_name();
		ResolvedReimbursementMngrDto reimb = new ResolvedReimbursementMngrDto(r.getR_id(), r.getAuthor_name(), r.getType_name(), r.getAmount(), r.getSubmitted().toGMTString(), r.getDescription(), resolverName, r.getStatus_name(), resolvedTimestamp);
		return reimb;
	}
	
	public EmployeeUserDto convertToEmployee(User u){
		EmployeeUserDto emp = new EmployeeUserDto(u.getId(), u.getEmail(), u.getF_name(), u.getL_name());
		return emp;
	}
	
	public List<Reimbursement> getPendingReimbursement(User u){
		List<Reimbursement> pending_list = new ReimbursementDaoImpl().getPendingReimbursementById(u);
		return pending_list;
	}
	
	public List<Reimbursement> getResolvedReimbursement(User u){
		List<Reimbursement> resolved_list = new ReimbursementDaoImpl().getResolvedReimbursementById(u);
		return resolved_list;
	}
	
	public List<User> retrieveAllEmployees(){
		List<User> employee_list = new UserDaoImpl().getAllEmployees();
		return employee_list;
	}
	
	public List<Reimbursement> retrieveAllPendingReimb(){
		List<Reimbursement> pending_list = new ReimbursementDaoImpl().getAllPendingReimbursement();
		return pending_list;
	}
	
	public List<Reimbursement> retrieveAllResolvedReimb(){
		List<Reimbursement> resolved_list = new ReimbursementDaoImpl().getAllResolvedReimbursement();
		return resolved_list;
	}
	
	public List<Reimbursement> retrieveAllEmpReimb(User u){
		List<Reimbursement> reimbursement_list = new ReimbursementDaoImpl().getAllReimbursementById(u);
		return reimbursement_list;
	}
	
	public void approveReimb(Reimbursement r, int resolver_id){
		new ReimbursementDaoImpl().approveReimbursementById(r, resolver_id);
	}
	
	public void denyReimb(Reimbursement r, int resolver_id){
		new ReimbursementDaoImpl().denyReimbursementById(r, resolver_id);
	}
	
	public Blob getBlobById(Reimbursement r){
		Blob result = new ReimbursementDaoImpl().getBlobById(r);
		return result;
	}
}
