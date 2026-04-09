package com.example.demo.entity;

import com.example.demo.pojo.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name="Member")
public class Member {
	@Id
	private String username;
	private String password;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Builder
	public Member(String username, String password, String name, Role role) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
	}
}
