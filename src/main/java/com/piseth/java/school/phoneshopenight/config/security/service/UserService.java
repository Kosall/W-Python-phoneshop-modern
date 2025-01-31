package com.piseth.java.school.phoneshopenight.config.security.service;

import java.util.Optional;

import com.piseth.java.school.phoneshopenight.config.based.secure.UserAuthor;

public interface UserService {
	Optional<UserAuthor> findingUserByUsername(String username);

}
