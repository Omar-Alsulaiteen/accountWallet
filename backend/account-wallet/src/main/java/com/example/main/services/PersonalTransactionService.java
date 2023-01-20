package com.example.main.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.exceptions.AccountNotFoundException;
import com.example.main.exceptions.LowBalanceException;
import com.example.main.exceptions.TransactionsNotFoundException;
import com.example.main.models.Account;
import com.example.main.models.PersonalTransaction;
import com.example.main.repositories.AccountRepository;
import com.example.main.repositories.PersonalTransactionRepository;

import jakarta.transaction.Transaction;

@Service
public class PersonalTransactionService {
	
	@Autowired
	PersonalTransactionRepository repo;
	
	@Autowired
	AccountRepository accountRepo;

	public Account deposit(PersonalTransaction transaction) {
		Optional<Account> optionalAccount = accountRepo.findByUsername(transaction.getAccount().getUsername());
		if (optionalAccount.isEmpty())
			throw new AccountNotFoundException();
		
		Account account = optionalAccount.get();
		
		account.setBalance(account.getBalance() + transaction.getAmount());
		accountRepo.save(account);
		
		transaction.setAccount(account);
		transaction.setTransactionType("deposite");
		repo.save(transaction);
		
		return account;
	}

	public Set<PersonalTransaction> getTransactions(String username){
		Set<PersonalTransaction> transactions = repo.findPersonalTransactionsByAccountUsernameOrderByDateDescTimeDesc(username);
		if(transactions.isEmpty()) {
			throw new TransactionsNotFoundException();
		}
		return transactions;
	}

	public Account withdraw(PersonalTransaction transaction) {
		Optional<Account> optionalAccount = accountRepo.findByUsername(transaction.getAccount().getUsername());
		Double newBalance;
		if (optionalAccount.isEmpty())
			throw new AccountNotFoundException();
		
		Account account = optionalAccount.get();
		
		newBalance = account.getBalance() - transaction.getAmount();
		if (newBalance < 0) {
			throw new LowBalanceException();
		}
		account.setBalance(account.getBalance() - transaction.getAmount());
		accountRepo.save(account);
		
		transaction.setAccount(account);
		transaction.setTransactionType("withdraw");
		repo.save(transaction);
		
		return account;
	}
}
