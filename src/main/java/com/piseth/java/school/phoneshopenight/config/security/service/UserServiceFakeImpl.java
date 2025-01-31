package com.piseth.java.school.phoneshopenight.config.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.config.based.secure.RoleEnum;
import com.piseth.java.school.phoneshopenight.config.based.secure.UserAuthor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService {
	private final PasswordEncoder encoder;
	

	@Override
	public Optional<UserAuthor> findingUserByUsername(String username) {
		List<UserAuthor> users=List.of(
				new UserAuthor("Nita",encoder.encode("nita012"), RoleEnum.ADMIN.getAuthorities(), true, true, true, true),
				new UserAuthor("pheary", encoder.encode("pheary0123"), RoleEnum.SALE.getAuthorities(), true, true, true, true)
				);		
		return users.stream().filter(p->p.getUsername().equals(username)).findFirst();
	}

}
