package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.ResponseMemberDto;
import com.example.demo.service.AccountService;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/member")
public class MemberController {
	
	@Autowired
	private MemberService mservice;
	
	@Autowired
	private AccountService aservice;
	
	@GetMapping("/mypage")
	public String mypage(Model model, HttpServletRequest request) {
		ResponseMemberDto dto = mservice.getMember(request);
		model.addAttribute("member", dto);
		model.addAttribute("account", aservice.findMemberAccount(dto.getId()));
		return "member/mypage";
	}
}
