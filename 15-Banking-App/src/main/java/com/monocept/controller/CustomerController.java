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

import com.monocept.entity.Customer;
import com.monocept.exception.AccountNotFoundException;
import com.monocept.service.ICustomerService;

@RestController
@RequestMapping("/customerapp")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private AccountController accntController;

	// get list of all customers
	@GetMapping("/customers")
	public List<Customer> findAll() {
		List<Customer> customers = customerService.findAll();
		return customers;
	}

	// save create customer
	@PostMapping("/save")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		customerService.save(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// save all
	@PostMapping("/saveall")
	public List<Customer> saveAllCustomers(@RequestBody List<Customer> customerList) {
		return customerService.saveAll(customerList);
	}

	// get account by cutomerId
	@GetMapping("/get/{cutomerId}")
	public Customer findById(@PathVariable int customerId) {
		Customer customer = null;
		customer = customerService.findById(customerId);
		if (customer == null) {
			throw new AccountNotFoundException("Customer with Customer id " + customerId + " is not found");
		}
		return customer;
	}

	// delete account by customerId
	@DeleteMapping("/delete/{custId}")
	public void delete(@PathVariable int custId) {
		customerService.deleteById(custId);
	}
	
	//add customer
//	@PostMapping("/")

//		//adding customers to bank
//		@PutMapping("/empid/{empId}/project/{projectId}")
//		public Employee assignProjectToEmployee(@PathVariable int empId,@PathVariable int projectId) {
//			return service.update(empId,projectId);
//		}
	
	@PutMapping("/customer/{customerId}/account/{accountNo}")
	public Customer assignAccountToCustomer(@PathVariable int customerId,@PathVariable int accountNo) {
		return customerService.update( customerId,accountNo);
	}
	
	@PutMapping("/deposit/account/{accNo}/amount/{amount}")
	public void depositAmount(@PathVariable int accNo, @PathVariable double amount) {
		customerService.depositBalance(accNo,amount);
	}
	
	@PutMapping("/withdraw/account/{accNo}/amount/{amount}")
	public void withdrawAmount(@PathVariable int accNo, @PathVariable double amount) {
		customerService.withdrawBalance(accNo,amount);
	}
	
	
	
	
	@PutMapping("/transfer/sender/{senderAccNo}/reciever/{recieverAccNo}/amount/{amount}")
	public void transferAmount(@PathVariable int senderAccNo, @PathVariable int recieverAccNo , @PathVariable double amount) {
		customerService.transferBalance(senderAccNo,recieverAccNo,amount);
	}

}
