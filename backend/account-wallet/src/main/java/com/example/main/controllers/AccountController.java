package com.example.main.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/{id}")
	public Optional<Account> accountData(@PathVariable Integer id) {
		return service.accountDataService(id);
	}
	
	@PostMapping
	public void createAccount(@RequestBody Account account) {
		service.createAccount(account);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable Integer id) {
		service.deleteAccount(id);
	}
	
	@GetMapping("/all-except/{id}")
	public ResponseEntity<Iterable<Account>> findAllExceptMine(@PathVariable Integer id) {
		return new ResponseEntity<>(service.findAllExceptMine(id), HttpStatus.OK);
	}

}
