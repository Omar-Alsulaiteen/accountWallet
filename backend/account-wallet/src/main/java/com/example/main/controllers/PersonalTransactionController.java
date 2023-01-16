package com.example.main.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.PersonalTransaction;
import com.example.main.services.PersonalTransactionService;

@RestController
@RequestMapping("personal")
public class PersonalTransactionController {

	@Autowired
	PersonalTransactionService service;
	
	@GetMapping("/transactions/{accountId}")
	public Set<PersonalTransaction> getTransactions(@PathVariable Integer accountId) {
		return service.getTransactions(accountId);
	}
	
	@PostMapping("/deposite")
	public void deposite(@RequestBody PersonalTransaction transaction) {
		service.deposite(transaction);
	}
	
	@PostMapping("/withdraw")
	public void withdraw(@RequestBody PersonalTransaction transaction) {
		service.withdraw(transaction);
	}


}
