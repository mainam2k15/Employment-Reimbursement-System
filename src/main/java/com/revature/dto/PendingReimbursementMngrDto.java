package com.revature.dto;

public class PendingReimbursementMngrDto {

	private int reimbursement_Id;
	private String author;
	private String reimbursement_type;
	private double amount;	
	private String submitted;
	private String description;
	
	public PendingReimbursementMngrDto(){
		
	}

	public PendingReimbursementMngrDto(int reimbursement_Id, String author, String reimbursement_Type, double amount,
			String submitted, String description) {
		super();
		this.reimbursement_Id = reimbursement_Id;
		this.author = author;
		this.reimbursement_type = reimbursement_Type;
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
	}

	public int getReimbursement_Id() {
		return reimbursement_Id;
	}

	public void setReimbursement_Id(int reimbursement_Id) {
		this.reimbursement_Id = reimbursement_Id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReimbursement_Type() {
		return reimbursement_type;
	}

	public void setReimbursement_Type(String reimbursement_type) {
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

	@Override
	public String toString() {
		return "PendingReimbursementMngrDto [reimbursement_Id=" + reimbursement_Id + ", author=" + author
				+ ", reimbursement_Type=" + reimbursement_type + ", amount=" + amount + ", submitted=" + submitted
				+ ", description=" + description + "]";
	}
	
}
