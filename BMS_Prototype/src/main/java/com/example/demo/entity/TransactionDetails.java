package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name="transaction_details")
public class TransactionDetails {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long tno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Account sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Account receiver;
	
	private String act;
	
	private Long balance;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime when;
	
	@Builder
	public TransactionDetails(Long sender, Long receiver, String act, Long balance) {
		this.sender = new Account();
		this.sender.setAcno(sender);
		
		this.receiver = new Account();
		this.receiver.setAcno(receiver);
		
		this.balance = balance;
	}
}
