package com.example.main.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.Account;
import com.example.main.models.GlobalTransaction;
import com.example.main.services.GlobalTransactionService;

@RestController
@RequestMapping("global")
public class GlobalTransactionController {

	@Autowired
	GlobalTransactionService service;
	
	@PostMapping("/transfer")
	public ResponseEntity<Account> transfer(@RequestBody GlobalTransaction transaction) {
		return new ResponseEntity<>(service.transfer(transaction), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("transactions/{username}")
	public Set<GlobalTransaction> getTransactions(@PathVariable String username) {
		return service.getTransactions(username);
	}

}
