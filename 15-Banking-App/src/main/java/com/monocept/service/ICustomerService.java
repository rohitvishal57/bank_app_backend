package com.monocept.service;

import java.util.List;

import com.monocept.entity.Customer;

public interface ICustomerService {

	List<Customer> findAll();

	void deleteById(int custId);

	Customer findById(int customerId);

	List<Customer> saveAll(List<Customer> customerList);

	Customer save(Customer customer);

	Customer update(int customerId, int accountNo);

	void depositBalance(int accNo, double amount);

	void withdrawBalance(int accNo, double amount);

	void transferBalance(int senderAccNo, int recieverAccNo, double amount);

}
