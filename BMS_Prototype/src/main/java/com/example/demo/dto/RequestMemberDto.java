package com.example.demo.dto;

import com.example.demo.entity.Member;
import com.example.demo.pojo.Role;

import lombok.Data;

@Data
public class RequestMemberDto {
	private String username;
	private String password;
	private String name;
	private Role role;
	
	public Member dtoToEntity() {
		return Member.builder()
				.username(username)
				.password(password)
				.name(name)
				.role(role)
				.build();
	}
}
