package com.example.demo.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberDetailsService implements UserDetailsService{
	
	private final MemberRepository repo;
	
	public MemberDetailsService(MemberRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member entity = repo.findByUsername(username);
		return entity == null ? null : new MemberDetails(entity);
	}

}
