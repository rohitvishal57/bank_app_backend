package com.monocept.service;

import java.util.List;

import com.monocept.entity.Bank;

public interface IBankService {

	void deleteById(int bankId);

	Bank findById(int bankId);

	Bank save(Bank bank);

	List<Bank> findAll();

	List<Bank> saveAll(List<Bank> bankList);

	Bank update(int bankId, int customerId);

}
