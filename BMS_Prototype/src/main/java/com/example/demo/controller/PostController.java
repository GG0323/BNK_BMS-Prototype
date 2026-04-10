package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestTransactionDetailsDto;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import com.example.demo.service.TransactionDetailsService;

@RestController
@RequestMapping("/api/2")
public class PostController {
	
	@Autowired
	private TransactionDetailsService tservice;
	
	@Autowired
	private AccountService aservice;
	
	@PostMapping("/log")
	public boolean log(RequestTransactionDetailsDto dto) {
		boolean flag = false;
		if(dto.getSender() == null || dto.getReceiver().equals(dto.getSender())) {
			Account depo = aservice.depo(dto.getReceiver(), dto.getBalance());
			flag = depo != null;
			flag = flag ? tservice.depo(depo, dto.getBalance()) : flag;
		}else {
			List<Account> sr = aservice.remit(dto);
			flag = sr != null;
			flag = flag ? tservice.save(sr.get(0), sr.get(1), dto.getBalance()) : flag;
		}

		return flag;
	}
	
	

}
