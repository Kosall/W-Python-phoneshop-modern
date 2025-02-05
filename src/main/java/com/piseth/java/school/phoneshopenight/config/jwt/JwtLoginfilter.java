package com.piseth.java.school.phoneshopenight.config.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class JwtLoginfilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager; 
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			
			throws AuthenticationException {
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			Login loginRequest = mapper.readValue(request.getInputStream(), Login.class);
			Authentication authentication=new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
		
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		
			Authentication authResult) throws IOException, ServletException {
		String secretKey="899iiuuyyiiiojhgfddssa233899iiuuyyiiiojhgfddssa67774899iiuuyyiiiojhgfddssa";
		String token = Jwts.builder()
			.setSubject(authResult.getName())
			.setIssuedAt(new Date())
			.claim("authorities", authResult.getAuthorities())
			//.setExpiration(LocalDateTime(LocalDateTime.now().plusMinutes(5)))
			.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusYears(3)))
			.setIssuer("modernshop.com")
			.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
		
		.compact();
		response.setHeader("Authorization","Bearer " + token);
	}

}
