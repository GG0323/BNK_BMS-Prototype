package com.example.demo.dto;

import com.example.demo.entity.TransactionDetails;

import lombok.Data;

@Data
public class RequestTransactionDetailsDto {
	private String sender;
	private String receiver;
	private Long balance;
	private String act;
	
	public TransactionDetails dtoTOEntity() {
		return TransactionDetails.builder()
				.sender(sender)
				.receiver(receiver)
				.balance(balance)
				.act(act)
				.build();
	}
}
