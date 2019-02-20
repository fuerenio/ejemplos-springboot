package com.gonzasilve.springboot.report;

import java.util.Date;

public class ReportDto {

	private int transactionId;
	private Double amount;
	private Date date;

	public ReportDto() {
		super();
	}

	public ReportDto(int transactionId, Double amount, Date date) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.date = date;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
