package com.example.demo.dto;

import com.example.demo.entity.Notice;

import lombok.Data;

@Data
public class RequestNoticeDto {
	private String title;
	private String context;
	
	public Notice dtoToEntity() {
		return Notice.builder()
				.title(title)
				.context(context)
				.build();
	}
}
