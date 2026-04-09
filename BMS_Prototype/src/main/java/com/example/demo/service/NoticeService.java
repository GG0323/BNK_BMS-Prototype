package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestNoticeDto;
import com.example.demo.dto.ResponseNoticeDto;
import com.example.demo.entity.Notice;
import com.example.demo.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	private final NoticeRepository repo;
	
	// 게시판 전체 조회
	public List<ResponseNoticeDto> getNotice(){
		return repo.findAll().stream().map(ResponseNoticeDto::entityToDto).toList();
	}
	
	// 게시판 1개 조회
	public ResponseNoticeDto getNotice(Long no) {
		Notice entity = repo.findById(no).orElseGet(null);
		return entity == null ? null : ResponseNoticeDto.entityToDto(entity);
	}
	
	// 게시판 등록 및 수정
	public boolean save(RequestNoticeDto dto) {
		return repo.save(dto.dtoToEntity()) != null;
	}
}
