package com.example.main.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.models.Account;
import com.example.main.services.AccountService;

@RestController
@RequestMapping("account")
public class AccountController {

	@Autowired
	AccountService service;
	
	@PostMapping("/login")
	public Account login(@RequestBody Account user) {
		return service.login(user);
	}
	
	@GetMapping("/{username}")
	public Optional<Account> accountData(@PathVariable String username) {
		return service.accountDataService(username);
	}
	
	@PostMapping
	public void createAccount(@RequestBody Account account) {
		service.createAccount(account);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable Integer id) {
		service.deleteAccount(id);
	}

}
