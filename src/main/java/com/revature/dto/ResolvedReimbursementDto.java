package com.revature.dto;

import java.sql.Timestamp;

public class ResolvedReimbursementDto {

	private int reimbursement_Id;
	private String reimbursement_type;
	private double amount;
	private String submitted;
	private String description;
	private String resolver_name;
	private String status_name;
	private String resolved;
	
	public ResolvedReimbursementDto(){
		
	}
	
	public ResolvedReimbursementDto(int reimbursement_Id, String reimbursement_type, double amount, String submitted,
			String description, String resolver_name, String status_name, String resolved) {
		super();
		this.reimbursement_Id = reimbursement_Id;
		this.reimbursement_type = reimbursement_type;
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.resolver_name = resolver_name;
		this.status_name = status_name;
		this.resolved = resolved;
	}

	public int getReimbursement_Id() {
		return reimbursement_Id;
	}
	public void setReimbursement_Id(int reimbursement_Id) {
		this.reimbursement_Id = reimbursement_Id;
	}
	public String getReimbursement_type() {
		return reimbursement_type;
	}
	public void setReimbursement_type(String reimbursement_type) {
		this.reimbursement_type = reimbursement_type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResolver_name() {
		return resolver_name;
	}
	public void setResolver_name(String resolver_name) {
		this.resolver_name = resolver_name;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
	}
	@Override
	public String toString() {
		return "ResolvedReimbursementDto [reimbursement_Id=" + reimbursement_Id + ", reimbursement_type="
				+ reimbursement_type + ", amount=" + amount + ", submitted=" + submitted + ", description="
				+ description + ", resolver_name=" + resolver_name + ", status_name=" + status_name + ", resolved="
				+ resolved + "]";
	}

}
