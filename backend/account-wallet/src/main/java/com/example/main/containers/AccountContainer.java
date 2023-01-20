package com.example.main.containers;

import com.example.main.models.Account;

public class AccountContainer {
	private String password;
	private Account account;
	
	
	
	public AccountContainer() {
	}
	
	public AccountContainer(String password, Account account) {
		this.password = password;
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
