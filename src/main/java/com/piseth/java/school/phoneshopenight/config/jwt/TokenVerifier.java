package com.piseth.java.school.phoneshopenight.config.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.piseth.java.school.phoneshopenight.exception.ApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
@Slf4j

public class TokenVerifier extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 String header = request.getHeader("Authorization");
		if(Objects.isNull(header)|| !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String tokening = header.replace("Bearer", "");
		String secretKey="899iiuuyyiiiojhgfddssa233899iiuuyyiiiojhgfddssa67774899iiuuyyiiiojhgfddssa";
		try {
			Jws<Claims> parseClaimsJws = Jwts.parser()
					.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
					.parseClaimsJws(tokening);
				  Claims body = parseClaimsJws.getBody();
				String username = body.getSubject();
				List<Map<String,String>> authorities = (List<Map<String, String>>) body.get("authorities");
				Set<SimpleGrantedAuthority> collect = authorities.stream().map(facebook->new SimpleGrantedAuthority(facebook.get("authority")))
				.collect(Collectors.toSet());
				
				Authentication authentication=new UsernamePasswordAuthenticationToken(username,null,collect);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		catch(ExpiredJwtException e) {
			log.info(e.getMessage());
			throw new ApiException(HttpStatus.BAD_REQUEST, "Token expired");
		}
		  
		filterChain.doFilter(request, response);
	}

}
