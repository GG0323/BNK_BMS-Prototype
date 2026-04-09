package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	
	private final JwtAuthFilter jwtAuthFilter;
	
	public FilterConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}
	
	@Bean
	FilterRegistrationBean<JwtAuthFilter> jwtFilter(){
		FilterRegistrationBean<JwtAuthFilter> bean = new FilterRegistrationBean<>();
		
		bean.setFilter(jwtAuthFilter);
		bean.addUrlPatterns("/api/*");
		bean.setOrder(1);
		
		return bean;
	}
}
