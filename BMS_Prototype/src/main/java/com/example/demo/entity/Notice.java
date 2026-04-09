package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(name = "notice")
public class Notice {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long no;
	private String title;
	private String context;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime ndate;
	
	@Builder
	public Notice(String title, String context) {
		this.title = title;
		this.context = context;
	}

}
