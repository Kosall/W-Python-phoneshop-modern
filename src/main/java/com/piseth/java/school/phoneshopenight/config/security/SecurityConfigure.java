package com.piseth.java.school.phoneshopenight.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.piseth.java.school.phoneshopenight.config.based.secure.RoleEnum;
import com.piseth.java.school.phoneshopenight.config.jwt.JwtLoginfilter;
import com.piseth.java.school.phoneshopenight.config.jwt.TokenVerifier;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true
		)
@RequiredArgsConstructor
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	private final PasswordEncoder encoder;
	private final UserDetailsService detailsService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.addFilter(new JwtLoginfilter(authenticationManager()))
		.addFilterAfter(new TokenVerifier(), JwtLoginfilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeHttpRequests()
		.antMatchers("/","css/**","js/**","index.html")
		.permitAll()
		//.antMatchers("/brands").hasRole("ADMIN")
		//.antMatchers("/colours").hasRole(RoleEnum.SALE.name())
		//.antMatchers(HttpMethod.POST, "/brands").hasAuthority(Permission.BRAND_WRITE.getDescription())
		//.antMatchers(HttpMethod.GET, "/brands").hasAuthority(Permission.BRAND_READ.getDescription())
		//.antMatchers(HttpMethod.GET, "/models").hasAuthority(Permission.MODEL_READ.getDescription())
		.anyRequest()
		.authenticated();
		
	}
	/*@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		//User user=new User("nary",encoder.encode("nary01"),Collections.emptyList());
	UserDetails build = User.builder()
				.username("Vireak")
				.password(encoder.encode("reaksa01"))
				//.roles("SALE")
				.authorities(RoleEnum.ADMIN.getAuthorities())
				.build();
	//User user=new User("nary",encoder.encode("nary01"),Collections.emptyList());
	UserDetails user = User.builder()
				.username("Reaksa")
				.password(encoder.encode("reaksa01"))
				//.roles("ADMIN")
				.authorities(RoleEnum.SALE.getAuthorities())
				.build();
	UserDetailsService service=new InMemoryUserDetailsManager(user,build);
		return service;
	}*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(detailsService);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
	}

}
