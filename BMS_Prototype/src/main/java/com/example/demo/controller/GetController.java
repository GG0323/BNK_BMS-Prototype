package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/api/1")
public class GetController {
	
	@Autowired
	private AccountService aservice;
	
	@GetMapping("/account/{id}")
	public ResponseEntity<List<Account>> getAccount(@PathVariable("id") Long id){
		return ResponseEntity.ok(aservice.findMemberAccount(id));
	}
	
	@GetMapping("/account")
	public boolean getAccount(@RequestParam("acn")String acn) {
		return aservice.findAccount(acn) != null;
	}
}
