package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.auth.MemberDetails;
import com.example.demo.pojo.Role;
import com.example.demo.utils.JwtUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private final JwtUtil util;
	
	public JwtLoginSuccessHandler(JwtUtil util) {
		this.util = util;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		MemberDetails member = (MemberDetails)authentication.getPrincipal();
		
		String username = member.getUsername();
		Role role = member.getRole();
		
		String accessToken = util.accessToken(username, role);
		String refleshToken = util.refleshToken(username, role);
		
		response.setHeader("Authorization", accessToken);
		
		Cookie cka = new Cookie("accessToken", accessToken);
		Cookie ckr = new Cookie("refleshToken", refleshToken);
		cka.setPath("/");
		ckr.setPath("/");
		
		response.addCookie(cka);
		response.addCookie(ckr);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{ \"result\" : \"true\", \"role\" : \"" + role + "\" }");
	}
	
}