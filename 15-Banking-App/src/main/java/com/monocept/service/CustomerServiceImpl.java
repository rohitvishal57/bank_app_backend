package com.monocept.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.entity.Customer;
import com.monocept.entity.Transaction;
import com.monocept.exception.AccountNotFoundException;
import com.monocept.exception.InsufficientBalanceException;
import com.monocept.entity.Account;
import com.monocept.repository.AccountRepository;
import com.monocept.repository.CustomerRepository;
import com.monocept.repository.TransactionRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	AccountRepository accountRepo;

	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	IAccountService accountService;

	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	@Override
	public void deleteById(int custId) {
		customerRepo.deleteById(custId);
	}

	@Override
	public Customer findById(int customerId) {
		return customerRepo.findById(customerId).get();
	}

	@Override
	public List<Customer> saveAll(List<Customer> customerList) {
		return customerRepo.saveAll(customerList);
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepo.save(customer);

	}

	@Override
	public Customer update(int customerId, int accountNo) {
		Customer customer = customerRepo.findById(customerId).get();
		Account account = accountRepo.findById(accountNo).get();

		Set<Account> accounts = customer.getAccounts();
		accounts.add(account);
		customer.setAccounts(accounts);

//		Set<Employee> employees = project.getEmployees();
//		employees.add(employee);
//		project.setEmployees(employees);
//		projRepo.save(project);

		return customerRepo.save(customer);

	}

	@Override
	public void depositBalance(int accNo, double amount) {
		Optional<Account> accountById = accountRepo.findById(accNo);
		if (accountById.isPresent()) {
			Account account = accountById.get();
			account.setBalance(account.getBalance() + amount);
			Transaction transaction = new Transaction(accNo, accNo, "deposit",
					new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()), amount);
			transactionRepo.save(transaction);
			accountService.update(accNo, transaction.getTransactionId());
			int custId = account.getCustomer().getCustomerId();
			Customer customer = customerRepo.findById(custId).get();
			customer.setTotalBalance(customer.getTotalBalance() + amount);
			customerRepo.save(customer);
			System.out.println(customer.getTotalBalance());
		} else {
			throw new AccountNotFoundException("Account Number does not exist");
		}

	}

	@Override
	public void withdrawBalance(int accNo, double amount) {
		Optional<Account> accountById = accountRepo.findById(accNo);
		if (accountById.isPresent()) {
			Account account = accountById.get();
			if (account.getBalance() >= amount) {
				account.setBalance(account.getBalance() - amount);
				Transaction transaction = new Transaction(accNo, accNo, "withdraw",
						new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()), amount);
				transactionRepo.save(transaction);
				accountService.update(accNo, transaction.getTransactionId());
				int custId = account.getCustomer().getCustomerId();
				Customer customer = customerRepo.findById(custId).get();
				customer.setTotalBalance(customer.getTotalBalance() - amount);
				customerRepo.save(customer);
				System.out.println(customer.getTotalBalance());
			} else {
				throw new InsufficientBalanceException("Insufficient balance");
			}

		} else {
			throw new AccountNotFoundException("Account Number does not exist");
		}

	}

	@Override
	public void transferBalance(int senderAccNo, int recieverAccNo, double amount) {
		Optional<Account> senderAccountById = accountRepo.findById(senderAccNo);
		Optional<Account> recieverAccountById = accountRepo.findById(recieverAccNo);
		if (senderAccountById.isPresent() && recieverAccountById.isPresent()) {
			Account senderAccount = senderAccountById.get();
			Account recieverAccount = recieverAccountById.get();
			if (senderAccount.getBalance() >= amount) {
				senderAccount.setBalance(senderAccount.getBalance() - amount);
				recieverAccount.setBalance(recieverAccount.getBalance() + amount);
				Transaction transaction = new Transaction(senderAccNo, recieverAccNo, "transfer",
						new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()), amount);
//				Transaction recieverTransaction = new Transaction(senderAccNo, recieverAccNo, "credit",
//						new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()), amount);
//				transactionRepo.save(senderTransaction);
//				transactionRepo.save(recieverTransaction);
				transactionRepo.save(transaction);
				accountService.update(senderAccNo, transaction.getTransactionId());
				accountService.update(recieverAccNo, transaction.getTransactionId());

				int senderCustId = senderAccount.getCustomer().getCustomerId();
				int recieverCustId = recieverAccount.getCustomer().getCustomerId();
				Customer senderCustomer = customerRepo.findById(senderCustId).get();
				Customer recieverCustomer = customerRepo.findById(recieverCustId).get();
				senderCustomer.setTotalBalance(senderCustomer.getTotalBalance() - amount);
				recieverCustomer.setTotalBalance(recieverCustomer.getTotalBalance() + amount);
				customerRepo.save(recieverCustomer);
				customerRepo.save(senderCustomer);
				
//				System.out.println(customer.getTotalBalance());
			} else {
				throw new InsufficientBalanceException("Insufficient balance");
			}

		} else {
			throw new AccountNotFoundException("Account Number does not exist");
		}

	}

}
