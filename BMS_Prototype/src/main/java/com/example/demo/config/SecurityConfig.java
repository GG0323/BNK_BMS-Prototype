package com.example.demo.config;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.pojo.Role;
import com.example.demo.utils.JwtUtil;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	private final JwtUtil util;
	
	public SecurityConfig(JwtUtil util) {
		this.util = util;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		
		http.formLogin(login ->
			login.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.successHandler(new JwtLoginSuccessHandler(util))
			.failureHandler(new JwtLoginFailHandler())
		);
		
		http.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());
		
		/*
		 * http.authorizeHttpRequests(request ->
		 * request.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).
		 * permitAll() .requestMatchers("/", "/loginForm", "/signup",
		 * "/member").permitAll()
		 * .requestMatchers("/api/**").hasAnyRole(Role.MEMBER.name(), Role.ADMIN.name())
		 * .requestMatchers("/api/member/**").hasRole(Role.MEMBER.name())
		 * .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name()) );
		 */

		
		return http.build();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
