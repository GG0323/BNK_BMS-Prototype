package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TransactionDetails;

import jakarta.transaction.Transactional;

@Transactional
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long>{
	
}
