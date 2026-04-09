package com.example.demo.dto;

import lombok.Data;

@Data
public class AccountDto {
	private Long acno;
	private Long balance;
	private boolean state;
	private String username; 
}
