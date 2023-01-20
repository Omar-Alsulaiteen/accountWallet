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

import com.example.main.containers.AccountContainer;
import com.example.main.containers.JwtResponse;
import com.example.main.containers.LoginRequest;
import com.example.main.models.Account;
import com.example.main.services.AccountService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@RestController
public class AccountController {

	@Autowired
	AccountService service;
	
	@PermitAll
	@PostMapping("login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest user) {
		return ResponseEntity.ok(new JwtResponse(service.login(user)));	}
	
	@GetMapping("account/{username}")
	public Optional<Account> accountData(@PathVariable String username) {
		return service.accountDataService(username);
	}
	
	@PermitAll
	@PostMapping("signup")
	public void createAccount(@RequestBody AccountContainer accountContainer) {
		service.createAccount(accountContainer);
	}
	
	@DeleteMapping("account/{username}")
	public void deleteAccount(@PathVariable String username) {
		service.deleteAccount(username);
	}
	
	@GetMapping("account/all-except/{username}")
	public ResponseEntity<Iterable<Account>> findAllExceptMine(@PathVariable String username) {
		return new ResponseEntity<>(service.findAllExceptMine(username), HttpStatus.OK);
	}

}
