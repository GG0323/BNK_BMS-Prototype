package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.RequestMemberDto;
import com.example.demo.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final MemberService mservice;
	
	@GetMapping("/")
	public String index() {
		return"index";
	}
	
	@GetMapping("/loginForm")
	public String login() {
		return "login";
	}
	
	@GetMapping(value = "/loginForm", params = "msg")
	public String login(@RequestParam("msg")int msg, Model model) {
		model.addAttribute("msg", msg == 1 ? "로그인 후 이용 바랍니다." : "로그인 시간이 만료되어 로그인 페이지로 이동합니다.");
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@PostMapping("/member")
	public @ResponseBody boolean member(RequestMemberDto dto) {
		mservice.save(dto);
		return true;
	}
}
