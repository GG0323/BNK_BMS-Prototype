package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestTransactionDetailsDto;
import com.example.demo.entity.Account;
import com.example.demo.entity.Member;
import com.example.demo.repository.AccountRepository;

import jakarta.transaction.Transactional;

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
	
	@Transactional
	public Account depo(String account, Long money) {
		Account entity = repo.findByAccountnum(account);
		entity.setBalance(entity.getBalance() + money);
		return repo.save(entity);
	}
	
	@Transactional
	public List<Account> remit(RequestTransactionDetailsDto dto) {
		Account sender = repo.findByAccountnum(dto.getSender());
		
		if(sender.getBalance() - dto.getBalance() < 0)
			return null;
		
		Account receiver = repo.findByAccountnum(dto.getReceiver());
		sender.setBalance(sender.getBalance() - dto.getBalance());
		receiver.setBalance(receiver.getBalance() + dto.getBalance());
		
		repo.save(sender);
		repo.save(receiver);
		return List.of(sender, receiver);
	}
	
	// 회원 기준으로 계좌 전체 조회
	public List<Account> findMemberAccount(Long id){
		return repo.findByMemberId(id);
	}
	
	// 계좌번호로 조회
	public Account findAccount(String acn) {
		return repo.findByAccountnum(acn);
	}
	
	// 계좌 동결 혹은 해제
	@Transactional
	public boolean freezenOrFree(Long id, boolean state) {
		System.out.println("서비스 도착");
		repo.updateState(id, !state);
		System.out.println("결과: " +  repo.findById(id));
		return true;
	}
}
