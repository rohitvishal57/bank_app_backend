package com.monocept.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.entity.Bank;
import com.monocept.entity.Customer;
import com.monocept.repository.BankRepository;
import com.monocept.repository.CustomerRepository;

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	private BankRepository bankRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public void deleteById(int bankId) {
		bankRepo.deleteById(bankId);

	}

	@Override
	public Bank findById(int bankId) {
		return bankRepo.findById(bankId).get();
	}

	@Override
	public Bank save(Bank bank) {
		return bankRepo.save(bank);
	}

	@Override
	public List<Bank> findAll() {
		return bankRepo.findAll();
	}

	@Override
	public List<Bank> saveAll(List<Bank> bankList) {
		return bankRepo.saveAll(bankList);
	}

	@Override
	public Bank update(int bankId, int customerId) {
		Bank bank = bankRepo.findById(bankId).get();
		Customer customer = customerRepo.findById(customerId).get();
		Set<Customer> customers = bank.getCustomers();
		customers.add(customer);
		bank.setCustomers(customers);

		return bankRepo.save(bank);

	}

}
