package com.example.main.exceptions;

public class AccountNotFoundException extends RuntimeException{
	private String msg;
	
	public AccountNotFoundException() {
		this.msg = "Account not found";
	}
	
	public AccountNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}
}
