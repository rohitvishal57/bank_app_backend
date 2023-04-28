package com.monocept.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;

	private String firstName;
	private String lastName;
	private double totalBalance;

	@JsonIgnore
	@ManyToMany(mappedBy = "customers",fetch=FetchType.LAZY)
	private Set<Bank> banks = new HashSet<Bank>();

	@JsonIgnoreProperties(value="customer")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_customer_id", referencedColumnName = "customer_id")
	private Set<Account> accounts = new HashSet<Account>();

	public Customer() {

	}

//	public Customer(int customerId, String firstName, String lastName, double totalBalance) {
//		super();
//		this.customerId = customerId;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.totalBalance = totalBalance;
//	}

	public Customer(int customerId, String firstName, String lastName, double totalBalance, Set<Account> accounts) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalBalance = totalBalance;
		this.accounts = accounts;

	}

//	public Customer(int customerId, String firstName, String lastName, double totalBalance, Set<Bank> banks) {
//		super();
//		this.customerId = customerId;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.totalBalance = totalBalance;
//		this.banks = banks;
//	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Set<Bank> getBanks() {
		return banks;
	}

	public void setBanks(Set<Bank> banks) {
		this.banks = banks;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

}
