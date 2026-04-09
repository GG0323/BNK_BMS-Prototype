package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.MemberService;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private MemberService mservice;
	
	@GetMapping("/mypage")
	public String welcome() {
		return "admin/mypage";
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		model.addAttribute("members", mservice.getMember());
		return "admin/member";
	}

}
