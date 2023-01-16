package com.example.main.repositories;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.main.models.Account;


@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{

	Iterable<Account> getAccountsById(Integer id);
	Iterable<Account> getAccountsByUsername(String username);
	Optional<Account> findByUsername(String username);

}
