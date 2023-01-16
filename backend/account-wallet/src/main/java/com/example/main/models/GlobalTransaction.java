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
public class GlobalTransaction {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	Account sender;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	Account receiver;

	@Column(nullable = false)
    @CreationTimestamp
	LocalDate date;
	
	@Column(nullable = false)
    @CreationTimestamp
	LocalTime time;
	
	
	@Column(nullable = false)
	Double amount;

	public GlobalTransaction(Integer id, Account sender, Account receiver, LocalDate date, LocalTime time,
			String tansactionType, Double amount) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
		this.time = time;
		this.amount = amount;
	}

	public GlobalTransaction() {
	}

	@Override
	public String toString() {
		return "GlobalTransaction [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", date=" + date
				+ ", time=" + time + ", amount=" + amount + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
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


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
