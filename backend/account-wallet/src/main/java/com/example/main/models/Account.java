package com.example.main.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;

	@Column(nullable = false, unique = true)
	String username;
	
	@Column(nullable = false)
	String password;

	@Column(nullable = false)
	String firstName;

	@Column(nullable = false)
	String lastName;

	Double balance;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	Set<PersonalTransaction> transactions;

	@JsonIgnore
	@OneToMany(mappedBy = "sender")
	Set<GlobalTransaction> transactionsAsSender;

	@JsonIgnore
	@OneToMany(mappedBy = "receiver")
	Set<GlobalTransaction> transactionsAsReceiver;

	public Account(Integer id, String username, String password, String firstName, String lastName, Double balance) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}

	public Account() {

	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", balance=" + balance + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Set<PersonalTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<PersonalTransaction> transactions) {
		this.transactions = transactions;
	}

	public Set<GlobalTransaction> getTransactionsAsSender() {
		return transactionsAsSender;
	}

	public void setTransactionsAsSender(Set<GlobalTransaction> transactionsAsSender) {
		this.transactionsAsSender = transactionsAsSender;
	}

	public Set<GlobalTransaction> getTransactionsAsReceiver() {
		return transactionsAsReceiver;
	}

	public void setTransactionsAsReceiver(Set<GlobalTransaction> transactionsAsReceiver) {
		this.transactionsAsReceiver = transactionsAsReceiver;
	}

}
