package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByUsername(String username);
	
	void deleteByUsername(String username);
}
