package com.example.demo.dto;

import lombok.Data;

@Data
public class AccountDto {
	private Long acno;
	private Long balance = 0L;
	private boolean state = true;
	private String username; 
}
