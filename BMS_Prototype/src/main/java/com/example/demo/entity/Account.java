package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter
@ToString
@NoArgsConstructor
@Table(name="account")
public class Account {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String accountnum;
	private Long balance = 0L;
	private boolean state = true;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
	
	
	@Builder
	public Account(Member member, String accountnum) {
		this.member = member;
		this.accountnum = accountnum;
	}
}
