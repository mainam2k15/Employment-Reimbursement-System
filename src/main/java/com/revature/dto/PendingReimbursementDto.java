package com.revature.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonPropertyOrder({"Reimbursement_Id", "Reimbursement_Type", "Amount", "Submitted", "Description"})
public class PendingReimbursementDto {

	private int Reimbursement_Id;
	private String Reimbursement_Type;
	private double Amount;	
	
	private String Submitted;
	
	private String Description;
	
	public PendingReimbursementDto() {
		
	}

	public PendingReimbursementDto(int reimbursement_Id, String reimbursement_Type, double amount, String submitted,
			String description) {
		super();
		Reimbursement_Id = reimbursement_Id;
		Reimbursement_Type = reimbursement_Type;
		Amount = amount;
		Submitted = submitted;
		Description = description;
	}

	public int getReimbursement_Id() {
		return Reimbursement_Id;
	}

	public void setReimbursement_Id(int reimbursement_Id) {
		Reimbursement_Id = reimbursement_Id;
	}

	public String getReimbursement_Type() {
		return Reimbursement_Type;
	}

	public void setReimbursement_Type(String reimbursement_Type) {
		Reimbursement_Type = reimbursement_Type;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public String getSubmitted() {
		return Submitted;
	}

	public void setSubmitted(String submitted) {
		Submitted = submitted;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "PendingReimbursementDto [Reimbursement_Id=" + Reimbursement_Id + ", Reimbursement_Type="
				+ Reimbursement_Type + ", Amount=" + Amount + ", Submitted=" + Submitted + ", Description="
				+ Description + "]";
	}
}
