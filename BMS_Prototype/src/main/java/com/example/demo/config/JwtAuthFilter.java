package com.example.demo.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private final JwtUtil util;
	
	public JwtAuthFilter(JwtUtil util) {
		this.util = util;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 1. api로 시작하는 주소가 아닐 때
		if(!request.getRequestURI().startsWith("/api")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String header = request.getHeader("Authorization");
		
		// 2. header가 null도 아니고, Bearer로 시작하며 토큰이 유효할 때
		if(header != null && header.startsWith("Bearer ") && util.isVaild(header.split(" ")[1])) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// 3. header가 null이거나 Bearer로 시작 안할때 cookie에서 accessToken 찾기
		String accessToken = findToken(request, "accessToken");
		
		// 4. cookie에 accessToken 있고, 유효할 때
		if(accessToken != null && util.isVaild(accessToken)) {
			filterChain.doFilter(request, response);
			return;
		}else if(accessToken != null) {
			
			// 5. accessToken이 유효하지 않을때 cookie에서 refleshToken 찾기
			String reflesh = findToken(request, "refleshToken");
			
			// 6. refleshToken이 null이 아니고 유효할 때
			if(reflesh != null && util.isVaild(reflesh)) {
				updateToken(response, reflesh);
				filterChain.doFilter(request, response);
				return;
			}else {
				// 7. refleshToken이 유효하지 않을 때
				sendErr(response, "2");
				return;
			}
		}
		
		// 8. header가 null이거나 쿠키에 token 자체가 없을 때
		sendErr(response, "1");
	}
	
	private String findToken(HttpServletRequest request, String tokenName) {
		for(Cookie ck : request.getCookies()) {
			if(ck.getName().equals(tokenName))
				return ck.getValue();
		}
		return null;
	}
	
	private void updateToken(HttpServletResponse response, String token) {
		
		Cookie access = new Cookie("accessToken", util.updateAccessToken(token));
		access.setPath("/");
		response.addCookie(access);
		
		if(util.checkReflesh(token)) {
			Cookie reflesh = new Cookie("refleshToken", util.updateRefleshToken(token));
			reflesh.setPath("/");
			response.addCookie(reflesh);
		}
	}
	
	private void sendErr(HttpServletResponse response, String msg) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.sendRedirect("/loginForm?msg = " + msg);
	}

}
