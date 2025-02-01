package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.config.based.secure.UserAuthor;
//import com.piseth.java.school.phoneshopenight.config.based.secure.UserAuthor;
import com.piseth.java.school.phoneshopenight.config.security.service.UserService;
import com.piseth.java.school.phoneshopenight.entity.User;
import com.piseth.java.school.phoneshopenight.exception.ApiException;
import com.piseth.java.school.phoneshopenight.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Primary
@Service
@RequiredArgsConstructor
public class RealUserServiceImpl implements UserService {
	
	private final UserRepository repository;
	
	@Override
	public Optional<UserAuthor> findingUserByUsername(String username) {
				 User users = repository.getUserByUsername(username)
						.orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"User not found"));
				UserAuthor author=UserAuthor.builder()
						.username(users.getUsername())
						.password(users.getPassword())
						.authorities(users.getRole().getAuthorities())
						.accountNonExpired(users.isAccountNonExpired())
						.accountNonLocked(users.isAccountNonLocked())
						.credentialsNonExpired(users.isCredentialsNonExpired())
						.enabled(users.isEnabled())
						.build();
				
		return Optional.ofNullable(author);
	
	}

}
