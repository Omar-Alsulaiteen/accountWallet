package com.example.main.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.exceptions.AccountNotFoundException;
import com.example.main.exceptions.LowBalanceException;
import com.example.main.exceptions.TransactionsNotFoundException;
import com.example.main.models.Account;
import com.example.main.models.GlobalTransaction;
import com.example.main.models.PersonalTransaction;
import com.example.main.repositories.AccountRepository;
import com.example.main.repositories.GlobalTransactionRepository;

@Service
public class GlobalTransactionService {

	@Autowired
	GlobalTransactionRepository repo;
	
	@Autowired
	AccountRepository accountRepo;
	
	public Account transfer(GlobalTransaction transaction) {
		Optional<Account> optionalSender = accountRepo.findById(transaction.getSender().getId());
		Optional<Account> optionalReceiver = accountRepo.findById(transaction.getReceiver().getId());

		Double newBalance;
		Double newReceiverBalance;
		
		if (optionalSender.isEmpty())
			throw new AccountNotFoundException();
		if (optionalReceiver.isEmpty())
			throw new AccountNotFoundException();

		Account sender = optionalSender.get();
		Account receiver = optionalReceiver.get();

		
		newBalance = sender.getBalance() - transaction.getAmount();
		newReceiverBalance = receiver.getBalance() + transaction.getAmount();

		if (newBalance < 0) {
			throw new LowBalanceException();
		}
		sender.setBalance(newBalance);
		accountRepo.save(sender);
		
		receiver.setBalance(newReceiverBalance);
		
		repo.save(transaction);
		return sender;

	}

	public Set<GlobalTransaction> getTransactions(Integer accountId) {
		Set<GlobalTransaction> transactions = repo.findGlobalTransactionsBySenderIdOrReceiverIdOrderByDateDescTimeDesc(accountId, accountId);
		if(transactions.isEmpty()) {
			throw new TransactionsNotFoundException();
		}
		return transactions;

	}
}
