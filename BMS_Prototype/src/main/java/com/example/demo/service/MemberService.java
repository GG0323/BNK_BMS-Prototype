package com.example.demo.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestMemberDto;
import com.example.demo.dto.ResponseMemberDto;
import com.example.demo.entity.Member;
import com.example.demo.pojo.Role;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository repo;
	private final BCryptPasswordEncoder pwEncoder;
	
	// 회원 가입 및 수정(성공하면 true, 실패하면 false)
	public Member save(RequestMemberDto dto) {
		dto.setPassword(pwEncoder.encode(dto.getPassword()));
		return repo.save(dto.dtoToEntity());
	}
	
	// 회원 전체 조회(관리자)
	public List<ResponseMemberDto> getMember(){
		return repo.findAll().stream().map(ResponseMemberDto::entityToDto).filter(dto -> dto.getRole().equals(Role.MEMBER)).toList();
	}
	
	// 회원 1명 조회(회원 본인)
	public ResponseMemberDto getMember(String username) {
		Member entity = repo.findByUsername(username);
		return entity == null ? null : ResponseMemberDto.entityToDto(entity);
	}
	
	// 회원 탈퇴
	public boolean deleteMember(String username) {
		if(getMember(username) == null) return false;
		
		repo.deleteByUsername(username);
		return true;
	}

}
