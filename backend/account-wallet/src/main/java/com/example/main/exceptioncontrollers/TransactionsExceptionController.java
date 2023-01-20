package com.example.main.exceptioncontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.main.exceptions.LowBalanceException;
import com.example.main.exceptions.TransactionsNotFoundException;

@ControllerAdvice
public class TransactionsExceptionController {

	@ExceptionHandler(value = TransactionsNotFoundException.class)
	public ResponseEntity<Object> exception(TransactionsNotFoundException exception) {
		return new ResponseEntity<>("No Transactoins found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = LowBalanceException.class)
	public ResponseEntity<Object> exception(LowBalanceException exception) {
		return new ResponseEntity<>("Low Balance", HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = java.lang.NullPointerException.class)
	public ResponseEntity<Object> exception(java.lang.NullPointerException
 exception) {
		return new ResponseEntity<>("Empty values are not allowed", HttpStatus.NOT_ACCEPTABLE);
	}


}
