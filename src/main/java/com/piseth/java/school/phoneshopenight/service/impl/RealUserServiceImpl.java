package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.config.based.secure.UserAuthor;
//import com.piseth.java.school.phoneshopenight.config.based.secure.UserAuthor;
import com.piseth.java.school.phoneshopenight.config.security.service.UserService;
import com.piseth.java.school.phoneshopenight.entity.Role;
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
						.authorities(getAuthorities(users.getRoles()))
						.accountNonExpired(users.isAccountNonExpired())
						.accountNonLocked(users.isAccountNonLocked())
						.credentialsNonExpired(users.isCredentialsNonExpired())
						.enabled(users.isEnabled())
						.build();
				
		return Optional.ofNullable(author);
	
	}
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roling) {
	
		Set<SimpleGrantedAuthority> collect = roling.stream().map((r->new SimpleGrantedAuthority("ROLE_" + r.getName()))).collect(Collectors.toSet());
		Set<SimpleGrantedAuthority> collect2 = roling.stream().flatMap(role->getStream(role)).collect(Collectors.toSet());
		collect2.addAll(collect);
		return collect2;	
			
		
		
		
	}
	private Stream<SimpleGrantedAuthority> getStream(Role re){
		return re.getPermissions().stream().map(p->new SimpleGrantedAuthority(p.getName()));
	}
	

}
