package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.Notice;

import lombok.Data;

@Data
public class ResponseNoticeDto {
	private Long no;
	private String title;
	private String context;
	private LocalDateTime ndate;
	
	public static ResponseNoticeDto entityToDto(Notice entity) {
		ResponseNoticeDto dto = new ResponseNoticeDto();
		dto.setNo(entity.getNo());
		dto.setTitle(entity.getTitle());
		dto.setContext(entity.getContext());
		dto.setNdate(entity.getNdate());
		return dto;
	}
}
