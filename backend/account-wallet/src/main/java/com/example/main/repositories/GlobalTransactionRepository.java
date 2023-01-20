package com.example.main.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.main.models.Account;
import com.example.main.models.GlobalTransaction;

@Repository
public interface GlobalTransactionRepository extends CrudRepository<GlobalTransaction, Integer>{

	Set<GlobalTransaction> findGlobalTransactionsBySenderUsernameOrReceiverUsernameOrderByDateDescTimeDesc(
			String username, String username2);

}
