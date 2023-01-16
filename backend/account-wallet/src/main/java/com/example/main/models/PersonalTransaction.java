package com.example.main.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PersonalTransaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	Account account;
	
	@Column(nullable = false)
    @CreationTimestamp
	LocalDate date;
	
	@Column(nullable = false)
    @CreationTimestamp
	LocalTime time;
	
	@Column(nullable = false)
	String tansactionType;
	
	@Column(nullable = false)
	Double amount;

	public PersonalTransaction(Integer id, Account account, LocalDate date, LocalTime time, String tansactionType,
			Double amount) {
		this.id = id;
		this.account = account;
		this.date = date;
		this.time = time;
		this.tansactionType = tansactionType;
		this.amount = amount;
	}

	public PersonalTransaction() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getTansactionType() {
		return tansactionType;
	}

	public void setTansactionType(String tansactionType) {
		this.tansactionType = tansactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PersonalTransaction [id=" + id + ", account=" + account + ", date=" + date + ", time=" + time
				+ ", tansactionType=" + tansactionType + ", amount=" + amount + "]";
	}
	
	
	
	
	
	
	
}
