package com.example.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.exceptions.AccountNotFoundException;
import com.example.main.exceptions.InvalidUsernameOrPasswordException;
import com.example.main.models.Account;
import com.example.main.repositories.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository repo;

	public Optional<Account> accountDataService(String username) {
		
		Optional<Account> account = repo.findByUsername(username);
		if(account.isEmpty()) {
			throw new AccountNotFoundException();
		}
		return account;
				
	}

	public void createAccount(Account account) {
		account.setBalance(0.0);
		repo.save(account);
	}

	public void deleteAccount(Integer id) {
		repo.deleteById(id);
	}

	public Account login(Account user) {
		Optional<Account> optionalValidatedUser = repo.findByUsername(user.getUsername());
		Account validatedUser;
		
		if(optionalValidatedUser.isEmpty())
			throw new InvalidUsernameOrPasswordException();
		
		validatedUser = optionalValidatedUser.get();
		
		if(!validatedUser.getPassword().equals(user.getPassword())) {
			throw new InvalidUsernameOrPasswordException();
		}
		
		return validatedUser;
	}

}
