package com.example.main.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.GlobalTransaction;
import com.example.main.services.GlobalTransactionService;

@RestController
@RequestMapping("global")
public class GlobalTransactionController {

	@Autowired
	GlobalTransactionService service;
	
	@PostMapping("/transfer")
	public void transfer(@RequestBody GlobalTransaction transaction) {
		service.transfer(transaction);
	}
	
	@GetMapping("/{accountId}")
	public Set<GlobalTransaction> getTransactions(@PathVariable Integer accountId) {
		return service.getTransactions(accountId);
	}

}
