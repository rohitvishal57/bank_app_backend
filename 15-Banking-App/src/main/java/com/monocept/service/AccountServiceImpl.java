package com.monocept.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.entity.Account;
import com.monocept.entity.Transaction;
import com.monocept.repository.AccountRepository;
import com.monocept.repository.TransactionRepository;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accntRepo;

	@Autowired
	private TransactionRepository transactionRepo;

	@Override
	public void deleteById(int accNo) {
		accntRepo.deleteById(accNo);

	}

	@Override
	public Account findById(int accNo) {
		return accntRepo.findById(accNo).get();
	}

	@Override
	public Account save(Account account) {
		return accntRepo.save(account);
	}

	@Override
	public List<Account> findAll() {
		return accntRepo.findAll();
	}

	@Override
	public List<Account> saveAll(List<Account> accntList) {
		return accntRepo.saveAll(accntList);
	}

	@Override
	public Account update(int accountNo, int transactionId) {
		Account account = accntRepo.findById(accountNo).get();
		Transaction trasnaction = transactionRepo.findById(transactionId).get();

		Set<Transaction> transactions = account.getTransactions();
		transactions.add(trasnaction);
		account.setTransactions(transactions);

		return accntRepo.save(account);

	}

//	@Override
//	public double getBalance(int accNo) {
//		return accntRepo.findBalanceByAcctNo(accNo);
//	}
}
