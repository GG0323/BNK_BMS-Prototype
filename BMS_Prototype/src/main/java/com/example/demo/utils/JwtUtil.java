package com.example.demo.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final SecretKey key;
	private final long expiration;
	
	public JwtUtil(@Value("${jwt.secret}")String key, @Value("${jwt.expiration}")Long expiration) {
		this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
		this.expiration = expiration;
	}
	
	public String accessToken(String username, String role) {
		return Jwts.builder()
				.subject(username)
				.claim("role", role)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key)
				.compact();
	}
	
	public String refleshToken(String username, String role) {
		return Jwts.builder()
				.subject(username)
				.claim("role", role)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration + 360000L))
				.signWith(key)
				.compact();
	}
	
	public String updateAccessToken(String token) {
		Claims data = parseToken(token);
		return accessToken(data.getSubject(), data.get("role", String.class));
	}
	
	public String updateRefleshToken(String token) {
		Claims data = parseToken(token);
		return refleshToken(data.getSubject(), data.get("role", String.class));
	}
	
	// refleshToken 만료 시간이 10분 미만이면 true 반환
	public boolean checkReflesh(String token) {
		return parseToken(token).getExpiration().getTime() - System.currentTimeMillis() < 10000L;
	}
	
	public Claims parseToken(String token) {
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}
	
	public boolean isVaild(String token) {
		try {
			parseToken(token);
			return true;
		}catch(JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	public String getUsername(String token) {
		return parseToken(token).getSubject();
	}
	
	public String getRole(String token) {
		return parseToken(token).get("role", String.class);
	}

}
