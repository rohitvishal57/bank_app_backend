package com.monocept.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_number")
	private int accountNumber;

	private String bank;
	private double balance;

	@ManyToOne
	@JoinColumn(name = "fk_customer_id")
	private Customer customer;

	@JsonIgnoreProperties(value="account")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_account_number", referencedColumnName = "account_number")
	private Set<Transaction> transactions = new HashSet<Transaction>();

	public Account() {

	}

//	public Account(int accountNumber, String bank) {
//		super();
//		this.accountNumber = accountNumber;
//		this.bank = bank;
//		this.balance = 1000;
//	}

	public Account(int accountNumber, String bank, Set<Transaction> transactions) {
		super();
		this.accountNumber = accountNumber;
		this.bank = bank;
		this.balance = 1000.0;
		this.transactions = transactions;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	

}
