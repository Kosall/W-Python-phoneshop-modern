package com.piseth.java.school.phoneshopenight.config.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	@Autowired
	private PasswordEncoder encoder;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","css/**","js/**","index.html")
		.permitAll()
		.antMatchers("/brands").hasRole("ADMIN")		
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		//User user=new User("nary",encoder.encode("nary01"),Collections.emptyList());
	UserDetails build = User.builder()
				.username("Vireak")
				.password(encoder.encode("reaksa01"))
				.roles("SALE")
				.build();
	//User user=new User("nary",encoder.encode("nary01"),Collections.emptyList());
	UserDetails user = User.builder()
				.username("Reaksa")
				.password(encoder.encode("reaksa01"))
				.roles("ADMIN")
				.build();
		UserDetailsService service=new InMemoryUserDetailsManager(user,build);
		return service;
	}

}
