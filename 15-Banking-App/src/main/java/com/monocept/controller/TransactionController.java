package com.monocept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.entity.Transaction;
import com.monocept.exception.AccountNotFoundException;
import com.monocept.service.ITransactionService;

@RestController
@RequestMapping("/transact")
public class TransactionController {

	@Autowired
	private ITransactionService transactionService;

	// get list of all transaction
	@GetMapping("/transactions")
	public List<Transaction> findAll() {
		List<Transaction> transactions = transactionService.findAll();
		return transactions;
	}

	// save
	@PostMapping("/save")
	public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
		transactionService.save(transaction);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// save all
	@PostMapping("/saveall")
	public List<Transaction> saveAllTransactions(@RequestBody List<Transaction> transactionList) {
		return transactionService.saveAll(transactionList);
	}

	// get account by transactionId
	@GetMapping("/get/{transactionId}")
	public Transaction findById(@PathVariable int transactionId) {
		Transaction transaction = null;
		transaction = transactionService.findById(transactionId);
		if (transaction == null) {
			throw new AccountNotFoundException("transaction withtransaction id " + transactionId + " is not found");
		}
		return transaction;
	}

	// delete transaction by transactionId
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		transactionService.deleteById(id);
	}

}
