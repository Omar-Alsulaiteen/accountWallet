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

	public void deposite(PersonalTransaction transaction) {
		Optional<Account> optionalAccount = accountRepo.findById(transaction.getAccount().getId());
		if (optionalAccount.isEmpty())
			throw new AccountNotFoundException();
		
		Account account = optionalAccount.get();
		
		account.setBalance(account.getBalance() + transaction.getAmount());
		accountRepo.save(account);
		
		
		transaction.setTansactionType("deposite");
		repo.save(transaction);
	}

	public Set<PersonalTransaction> getTransactions(Integer accountId) {
		Set<PersonalTransaction> transactions = repo.findPersonalTransactionsByAccountIdOrderByDateDescTimeDesc(accountId);
		if(transactions.isEmpty()) {
			throw new TransactionsNotFoundException();
		}
		return transactions;
	}

	public void withdraw(PersonalTransaction transaction) {
		Optional<Account> optionalAccount = accountRepo.findById(transaction.getAccount().getId());
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
		
		transaction.setTansactionType("withdraw");
		repo.save(transaction);

	}
}
