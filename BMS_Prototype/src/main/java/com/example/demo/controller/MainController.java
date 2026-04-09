package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
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
		model.addAttribute("msg", msg == 1 ? "":"");
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
}
