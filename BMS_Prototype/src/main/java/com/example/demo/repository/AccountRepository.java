package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	@Query(value="select accountno_seq.nextval from dual", nativeQuery = true)
	Long getAccountNo();
}
