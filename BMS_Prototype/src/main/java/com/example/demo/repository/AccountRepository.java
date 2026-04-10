package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Account;

import jakarta.transaction.Transactional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Long>{
	@Query(value="select accountno_seq.nextval from dual", nativeQuery = true)
	Long getAccountNo();
	
	@Query("select a from Account a where a.member.id = :id")
	List<Account> findByMemberId(@Param("id")Long id);
	
	@Modifying
	@Query("update Account a set a.state = :state where a.id = :id")
	void updateState(@Param("id")Long id, @Param("state")boolean state);
	
	Account findByAccountnum(String accountnum);
}
