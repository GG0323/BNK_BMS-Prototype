package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.TransactionDetails;
import com.example.demo.repository.TransactionDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionDetailsService {
	
	private final TransactionDetailsRepository repo;
	
	public boolean depo(Account receiver, Long balance) {
		return repo.save(TransactionDetails.builder().receiver(receiver).balance(balance).build()) != null;
	}
	
	public boolean save(Account sender, Account receiver, Long balance) {
		return repo.save(TransactionDetails.builder().sender(sender).receiver(receiver).balance(balance).build()) != null;
	}
}
