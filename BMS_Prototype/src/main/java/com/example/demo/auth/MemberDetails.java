package com.example.demo.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Member;
import com.example.demo.pojo.Role;

public class MemberDetails implements UserDetails{
	
	private final Member entity;
	
	public MemberDetails(Member entity) {
		this.entity = entity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {

			@Override
			public @Nullable String getAuthority() {
				return "ROLE_" + entity.getRole();
			}
		});
		return collection;
	}

	@Override
	public @Nullable String getPassword() {
		return entity.getPassword();
	}

	@Override
	public String getUsername() {
		return entity.getUsername();
	}
	
	public String getName() {
		return entity.getName();
	}
	
	public Role getRole() {
		return entity.getRole();
	}
}
