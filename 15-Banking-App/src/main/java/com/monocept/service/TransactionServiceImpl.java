package com.monocept.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.entity.Transaction;
import com.monocept.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	TransactionRepository transactionRepo;

	@Override
	public List<Transaction> findAll() {
		return transactionRepo.findAll();
	}

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRepo.save(transaction);

	}

	@Override
	public List<Transaction> saveAll(List<Transaction> transactionList) {
		return transactionRepo.saveAll(transactionList);
	}

	@Override
	public Transaction findById(int transactionId) {
		return transactionRepo.findById(transactionId).get();
	}

	@Override
	public void deleteById(int id) {
		transactionRepo.deleteById(id);

	}

}
