package com.piseth.java.school.phoneshopenight.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
	@Bean
 public PasswordEncoder gePasswordEncoder() {
	 PasswordEncoder cryptPasswordEncoder=new BCryptPasswordEncoder();
	 return cryptPasswordEncoder;
 }
}
