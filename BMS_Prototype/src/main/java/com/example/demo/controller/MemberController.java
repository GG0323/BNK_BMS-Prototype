package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("member", mservice.getMember(request));
		return "member/mypage";
	}
	
	@GetMapping("/accounts")
	public String accounts(@RequestParam("id")Long id, Model model) {
		ResponseMemberDto dto = mservice.getMember(id);
		model.addAttribute("name", dto.getName());
		model.addAttribute("account", aservice.findMemberAccount(dto.getId()));
		return "member/accounts";
	}
	
	@GetMapping("/deposit")
	public String deposit(@RequestParam("id")Long id, Model model) {
		model.addAttribute("account", aservice.findMemberAccount(id));
		return "member/deposit";
	}
	
	@GetMapping("/remit")
	public String remit(@RequestParam("id")Long id, Model model) {
		model.addAttribute("account", aservice.findMemberAccount(id));
		return "member/remit";
	}
}
