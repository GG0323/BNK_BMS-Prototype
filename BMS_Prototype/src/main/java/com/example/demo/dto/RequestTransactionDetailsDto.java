package com.example.demo.dto;

import lombok.Data;

@Data
public class RequestTransactionDetailsDto {
	private String sender;
	private String receiver;
	private Long balance;
	private String act;
	
}
