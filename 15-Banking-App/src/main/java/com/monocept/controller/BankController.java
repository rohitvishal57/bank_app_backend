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

import com.monocept.entity.Bank;
import com.monocept.exception.AccountNotFoundException;
import com.monocept.service.IBankService;

@RestController
@RequestMapping("/ebankapp")
public class BankController {

	@Autowired
	private IBankService bankService;

	// get list of all banks
	@GetMapping("/banks")
	public List<Bank> findAll() {
		List<Bank> banks = bankService.findAll();
		return banks;
	}

	// save
	@PostMapping("/save")
	public ResponseEntity<Bank> saveBank(@RequestBody Bank bank) {
		bankService.save(bank);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// save all
	@PostMapping("/saveall")
	public List<Bank> saveAllBanks(@RequestBody List<Bank> bankList) {
		return bankService.saveAll(bankList);
	}

	// get bank by id
	@GetMapping("/get/{bankId}")
	public Bank findById(@PathVariable int bankId) {
		Bank bank = null;
		bank = bankService.findById(bankId);
		if (bank == null) {
			throw new AccountNotFoundException("Bank with id " + bankId + " is not found");
		}
		return bank;
	}

	// delete bank by id
	@DeleteMapping("/delete/{bankId}")
	public void delete(@PathVariable int bankId) {
		bankService.deleteById(bankId);
	}

	// adding customers to bank
	@PutMapping("/bankid/{bankId}/customer/{customerId}")
	public Bank assignCustomerToBank(@PathVariable int bankId, @PathVariable int customerId) {
		return bankService.update(bankId, customerId);
	}
	

}
