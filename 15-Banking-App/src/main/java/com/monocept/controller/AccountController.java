package com.monocept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.entity.Account;
import com.monocept.exception.AccountNotFoundException;
import com.monocept.service.IAccountService;

@RestController
@RequestMapping("/accountapp")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	// get list of all banks
	@GetMapping("/accounts")
	public List<Account> findAll() {
		List<Account> accounts = accountService.findAll();
		return accounts;
	}

	// save
	@PostMapping("/save")
	public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
		accountService.save(account);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// save all
	@PostMapping("/saveall")
	public List<Account> saveAllAccounts(@RequestBody List<Account> accountList) {
		return accountService.saveAll(accountList);
	}

	// get account by acc no
	@GetMapping("/get/{accNo}")
	public Account findById(@PathVariable int accNo) {
		Account accnt = null;
		accnt = accountService.findById(accNo);
		if (accnt == null) {
			throw new AccountNotFoundException("Account with account number " + accNo + " is not found");
		}
		return accnt;
	}

	// delete account by accNo
	@DeleteMapping("/delete/{accNo}")
	public void delete(@PathVariable int accNo) {
		accountService.deleteById(accNo);
	}

	// adding transactions to account
	@PutMapping("/accountNumber/{accountNo}/transactionid/{transactionId}")
	public Account assignTransactionToAccount(@PathVariable int accountNo, @PathVariable int transactionId) {
		return accountService.update(accountNo, transactionId);
	}

//	//check balance
//	@GetMapping("/account/{accNo}/balance")
//	public double getBalance(@PathVariable int accNo) {
//		return accountService.getBalance(accNo);
//	}
//	
//	// deposit Amount
//	@PutMapping("/account/{accNo}/deposit/{amount}")
//	public void depositAmount(@PathVariable int accNo, @PathVariable double amount) {
//		int initialBalance = getBalance(accNo);
//	}

}
