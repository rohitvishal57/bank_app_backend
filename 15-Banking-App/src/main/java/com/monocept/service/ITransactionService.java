package com.monocept.service;

import java.util.List;

import com.monocept.entity.Transaction;

public interface ITransactionService {

	List<Transaction> findAll();

	Transaction save(Transaction transaction);

	List<Transaction> saveAll(List<Transaction> transactionList);

	Transaction findById(int transactionId);

	void deleteById(int id);



}
