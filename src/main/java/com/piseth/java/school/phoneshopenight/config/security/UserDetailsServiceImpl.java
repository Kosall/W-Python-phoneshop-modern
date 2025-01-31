package com.piseth.java.school.phoneshopenight.config.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.config.security.service.UserService;
import com.piseth.java.school.phoneshopenight.exception.ApiException;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
		private final UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userService.findingUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		
	}

}
