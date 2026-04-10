package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AccountService;
import com.example.demo.service.MemberService;

@RestController
@RequestMapping("/api/3")
public class PatchController {
	
	@Autowired
	private MemberService mservice;
	
	@Autowired
	private AccountService aservice;
	
	@PutMapping	("/member/{id}/{state}")
	public boolean freezen(@PathVariable("id")Long id, @PathVariable("state")boolean state) {
		return aservice.freezenOrFree(id, state);
	}

}
