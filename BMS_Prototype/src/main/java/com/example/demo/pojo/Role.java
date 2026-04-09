package com.example.demo.pojo;

public enum Role {
	ADMIN,
	MEMBER;
	
	@Override
	public String toString() {
		return name();
	}
}
