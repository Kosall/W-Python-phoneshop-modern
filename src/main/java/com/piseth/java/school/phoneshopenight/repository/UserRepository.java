package com.piseth.java.school.phoneshopenight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.config.based.secure.UserAuthor;
import com.piseth.java.school.phoneshopenight.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> getUserByUsername(String username);

}
