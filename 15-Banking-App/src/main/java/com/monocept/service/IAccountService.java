package com.monocept.service;

import java.util.List;

import com.monocept.entity.Account;

public interface IAccountService {

	List<Account> findAll();

	Account save(Account account);

	List<Account> saveAll(List<Account> accountList);

	Account findById(int accNo);

	void deleteById(int accNo);

	Account update(int accountNo, int transactionId);

//	double getBalance(int accNo);

}
