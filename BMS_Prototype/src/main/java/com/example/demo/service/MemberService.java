package com.example.demo.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestMemberDto;
import com.example.demo.dto.ResponseMemberDto;
import com.example.demo.entity.Member;
import com.example.demo.pojo.Role;
import com.example.demo.repository.MemberRepository;
import com.example.demo.utils.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository repo;
	private final BCryptPasswordEncoder pwEncoder;
	private final JwtUtil util;
	
	// 회원 가입 및 수정(성공하면 true, 실패하면 false)
	public Member save(RequestMemberDto dto) {
		dto.setPassword(pwEncoder.encode(dto.getPassword()));
		return repo.save(dto.dtoToEntity());
	}
	
	// 회원 전체 조회(관리자)
	public List<ResponseMemberDto> getMember(){
		return repo.findAll().stream().map(ResponseMemberDto::entityToDto).filter(dto -> dto.getRole().equals(Role.MEMBER)).toList();
	}
	
	// 회원 1명 username으로 조회
	public ResponseMemberDto getMember(String username) {
		Member entity = repo.findByUsername(username);
		return entity == null ? null : ResponseMemberDto.entityToDto(entity);
	}
	
	public ResponseMemberDto getMember(HttpServletRequest request) {
		String token = null;
		for(Cookie ck : request.getCookies())
			if(ck.getName().equals("accessToken")) 
				token = ck.getValue();

		return token == null? null : getMember(util.getUsername(token));
	}
	
	// 회원 1명 id로 조회
	public ResponseMemberDto getMember(Long id) {
		Member entity = repo.findById(id).orElse(null);
		return entity == null ? null : ResponseMemberDto.entityToDto(entity);
	}
	
	// 회원 탈퇴
	public boolean deleteMember(String username) {
		if(getMember(username) == null) return false;
		
		repo.deleteByUsername(username);
		return true;
	}

}
