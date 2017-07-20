package com.revature.pojo;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Reimbursement implements Comparable<Reimbursement>{

	private int r_id;
	private int author;
	private String author_name; //first + last
	private int resolver;
	private String resolver_name;
	private int status_id;
	private String status_name;
	private int type_id;
	private String type_name;
	private double amount;
	
	private Timestamp submitted;
	private Timestamp resolved;
	
	private String description;
	
	public Reimbursement(){
		
	}

	public Reimbursement(int r_id, int author, int resolver, int status_id, int type_id, double amount,
			Timestamp submitted, Timestamp resolved, String description, String author_name, String resolver_name, String status_name, String type_name) {
		super();
		this.r_id = r_id;
		this.author = author;
		this.resolver = resolver;
		this.status_id = status_id;
		this.type_id = type_id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author_name = author_name;
		this.resolver_name = resolver_name;
		this.status_name = status_name;
		this.type_name = type_name;
	}
	
	public Reimbursement(int r_id, int author, int resolver, int status_id, int type_id, double amount,
			Timestamp submitted, Timestamp resolved, String description) {
		super();
		this.r_id = r_id;
		this.author = author;
		this.resolver = resolver;
		this.status_id = status_id;
		this.type_id = type_id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
	}

	public Reimbursement(int author, int status_id, int type_id, double amount, Timestamp submitted,
			String description) {
		super();
		this.author = author;
		this.status_id = status_id;
		this.type_id = type_id;
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
	}
	
	

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
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

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@Override
	public String toString() {
		return "Reimbursement [r_id=" + r_id + ", author=" + author + ", author_name=" + author_name + ", resolver="
				+ resolver + ", resolver_name=" + resolver_name + ", status_id=" + status_id + ", status_name="
				+ status_name + ", type_id=" + type_id + ", type_name=" + type_name + ", amount=" + amount
				+ ", submitted=" + submitted + ", resolved=" + resolved + ", description=" + description + "]";
	}

	@Override
	public int compareTo(Reimbursement o) {

		if(this.getR_id() < o.getR_id())
			return -1;
		else
			return 1;
	}
}
