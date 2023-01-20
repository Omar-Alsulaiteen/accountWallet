package com.example.main.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.main.models.Account;
import com.example.main.models.PersonalTransaction;

@Repository
public interface PersonalTransactionRepository extends CrudRepository<PersonalTransaction, Integer>{
	
		Iterable<PersonalTransaction> findAccountsByDate(LocalDate date);
		Iterable<PersonalTransaction> findAccountsByDateBetween(LocalDate date1, LocalDate date2);
		Set<PersonalTransaction> findPersonalTransactionsByAccountUsernameOrderByDateDescTimeDesc(String username);
}
