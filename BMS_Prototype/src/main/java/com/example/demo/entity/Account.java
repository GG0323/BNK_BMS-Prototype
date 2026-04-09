package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name="account")
public class Account {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long acno;
	private Long balance = 0L;
	private boolean state = true;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Member username;
	
	@Builder
	public Account(String username) {
		this.username = new Member();
		this.username.setUsername(username);
	}
}
