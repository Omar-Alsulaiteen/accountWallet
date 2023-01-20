package com.example.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.main.config.MyUserDetails;
import com.example.main.models.Account;
import com.example.main.repositories.AccountRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountRepository repo; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = repo.findByUsername(username);
		
		if(account.isEmpty())
			throw new UsernameNotFoundException("Username was not found");
		
		return account.map(MyUserDetails::new).get();
	}

}
