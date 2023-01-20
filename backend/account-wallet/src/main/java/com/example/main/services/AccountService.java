package com.example.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.main.config.MyUserDetails;
import com.example.main.containers.AccountContainer;
import com.example.main.containers.LoginRequest;
import com.example.main.exceptions.AccountNotFoundException;
import com.example.main.models.Account;
import com.example.main.repositories.AccountRepository;
import com.example.main.util.JwtUtil;

@Service
public class AccountService {
	
    @Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private AccountRepository repo;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Optional<Account> accountDataService(String username) {
		
		Optional<Account> account = repo.findByUsername(username);
		if(account.isEmpty()) {
			throw new AccountNotFoundException();
		}
		return account;
				
	}

	public void createAccount(AccountContainer accountContainer) {
		Account account = accountContainer.getAccount();
		account.setBalance(0.0);
		account.setPassword(encoder.encode(accountContainer.getPassword()));
		repo.save(account);
	}

	public void deleteAccount(String username) {
		repo.deleteByUsername(username);
	}

	public String login(LoginRequest loginRequest) {
		Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                    )
                );
            SecurityContextHolder.getContext().setAuthentication(auth);
            Account account = ((MyUserDetails) auth.getPrincipal()).getAccount();
            final String jwt = jwtTokenUtil.generateToken((UserDetails) auth.getPrincipal());
            
            return jwt;
            
//		Optional<Account> optionalValidatedUser = repo.findByUsername(user.getUsername());
//		Account validatedUser;
//		
//		if(optionalValidatedUser.isEmpty())
//			throw new InvalidUsernameOrPasswordException();
//		
//		validatedUser = optionalValidatedUser.get();
//		
//		if(!validatedUser.getPassword().equals(user.getPassword())) {
//			throw new InvalidUsernameOrPasswordException();
//		}
//		
//		return validatedUser;
	}

	public Iterable<Account> findAllExceptMine(String username) {
		Iterable<Account> accounts = repo.findAccountsExcludeMine(username);
		return accounts;
	}

}
