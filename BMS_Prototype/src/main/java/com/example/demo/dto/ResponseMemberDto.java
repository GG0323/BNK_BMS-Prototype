package com.example.demo.dto;

import com.example.demo.entity.Member;
import com.example.demo.pojo.Role;

import lombok.Data;

@Data
public class ResponseMemberDto {
	private String username;
	private String name;
	private Role role;
	
	public static ResponseMemberDto entityToDto(Member entity) {
		ResponseMemberDto dto = new ResponseMemberDto();
		dto.username = entity.getUsername();
		dto.name = entity.getName();
		dto.role = entity.getRole();
		return dto;
	}

}
