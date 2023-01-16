package com.example.main.exceptioncontrollers;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.main.exceptions.AccountNotFoundException;
import com.example.main.exceptions.InvalidUsernameOrPasswordException;

@ControllerAdvice
public class AccountExcpetionCotroller {
	@ExceptionHandler(value = AccountNotFoundException.class)
	public ResponseEntity<Object> exception(AccountNotFoundException exception) {
		return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Object> exception(SQLIntegrityConstraintViolationException exception) {
		return new ResponseEntity<>("Database Error: Duplicate Unique Values ", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = PropertyValueException.class)
	public ResponseEntity<Object> exception(PropertyValueException exception) {
		return new ResponseEntity<>("Not Null Constraint", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = InvalidUsernameOrPasswordException.class)
	public ResponseEntity<Object> exception(InvalidUsernameOrPasswordException exception) {
		return new ResponseEntity<>("Invalid username or password", HttpStatus.NOT_ACCEPTABLE);
	}



}
