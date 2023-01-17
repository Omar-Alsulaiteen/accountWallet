package com.example.main.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.Account;
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
	
	@PostMapping("/deposit")
	public ResponseEntity<Account> deposit(@RequestBody PersonalTransaction transaction) {
		return new ResponseEntity<>(service.deposit(transaction), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Account> withdraw(@RequestBody PersonalTransaction transaction) {
		return new ResponseEntity<>(service.withdraw(transaction), HttpStatus.ACCEPTED);
	}


}
