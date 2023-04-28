package com.monocept.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private int transactionId;

	private int senderAccountNumber;
	private int recieverAccountNumber;
	private String transactionType;
	private double amount;
	private String time;
//	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "fk_account_number")
	private Account account;

	public Transaction() {

	}

	public Transaction(int senderAccountNumber, int recieverAccountNumber, String transactionType,
			double amount,String time, Account account) {
		super();
		this.senderAccountNumber = senderAccountNumber;
		this.recieverAccountNumber = recieverAccountNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		this.account = account;
		this.time = time;
	}

	public Transaction(int senderAccNo, int recieverAccNo, String transactionType, String time, double amount) {
		super();
		this.senderAccountNumber = senderAccNo;
		this.recieverAccountNumber = recieverAccNo;
		this.transactionType = transactionType;
		this.amount = amount;
		this.time = time;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getSenderAccountNumber() {
		return senderAccountNumber;
	}

	public void setSenderAccountNumber(int senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}

	public int getRecieverAccountNumber() {
		return recieverAccountNumber;
	}

	public void setRecieverAccountNumber(int recieverAccountNumber) {
		this.recieverAccountNumber = recieverAccountNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

//	public LocalDate getDate() {
//		return date;
//	}
//
//	public void setDate(LocalDate date) {
//		this.date = date;
//	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

//	public Timestamp getTime() {
//		return time;
//	}
//
//	public void setTime(Timestamp time) {
//		this.time = time;
//	}

}
