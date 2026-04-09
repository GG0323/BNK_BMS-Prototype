package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Member;
import com.example.demo.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository repo;
	
	// 계좌 자동 생성(성공하면 true, 실패하면 false)
	public boolean save(Member entity) {
		String pd = String.format("%013d", repo.getAccountNo());
		String account = pd.substring(0, 3) + "-" + pd.substring(3, 7) + "-" + pd.substring(7, 13);
		return repo.save(Account.builder().member(entity).accountnum(account).build()) != null;
	}
	
	

}
