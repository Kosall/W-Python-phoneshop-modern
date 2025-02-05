package com.piseth.java.school.phoneshopenight.config.security;

import java.lang.reflect.Method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.piseth.java.school.phoneshopenight.config.FilterChainExceptionHandler;
import com.piseth.java.school.phoneshopenight.config.based.secure.Permission;
import com.piseth.java.school.phoneshopenight.config.jwt.JwtLoginfilter;
import com.piseth.java.school.phoneshopenight.config.jwt.TokenVerifier;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfigure {
	private final PasswordEncoder encoder;
	private final UserDetailsService detailsService;
	private final AuthenticationConfiguration authenticationConfiguration;
	private final FilterChainExceptionHandler exceptionHandler;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				
				.addFilter(new JwtLoginfilter(authenticationManager(authenticationConfiguration)))
				.addFilterBefore(exceptionHandler, JwtLoginfilter.class)
				.addFilterAfter(new TokenVerifier(), JwtLoginfilter.class)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeHttpRequests()
				.antMatchers("/", "css/**", "js/**", "index.html").permitAll().antMatchers(HttpMethod.PUT, "/brands/**")
				.hasAuthority(Permission.BRAND_UPDATE.getDescription())
				// .antMatchers("/brands").hasRole("ADMIN")
				// .antMatchers("/colours").hasRole(RoleEnum.SALE.name())
				// .antMatchers(HttpMethod.POST,
				// "/brands").hasAuthority(Permission.BRAND_WRITE.getDescription())
				// .antMatchers(HttpMethod.GET,
				// "/brands").hasAuthority(Permission.BRAND_READ.getDescription())
				// .antMatchers(HttpMethod.GET,
				// "/models").hasAuthority(Permission.MODEL_READ.getDescription())
				.anyRequest().authenticated();
		DefaultSecurityFilterChain build = http.build();
		return build;

	}

	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() { //User user=new
	 * User("nary",encoder.encode("nary01"),Collections.emptyList()); UserDetails
	 * build = User.builder() .username("Vireak")
	 * .password(encoder.encode("reaksa01")) //.roles("SALE")
	 * .authorities(RoleEnum.ADMIN.getAuthorities()) .build(); //User user=new
	 * User("nary",encoder.encode("nary01"),Collections.emptyList()); UserDetails
	 * user = User.builder() .username("Reaksa")
	 * .password(encoder.encode("reaksa01")) //.roles("ADMIN")
	 * .authorities(RoleEnum.SALE.getAuthorities()) .build(); UserDetailsService
	 * service=new InMemoryUserDetailsManager(user,build); return service; }
	 */

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}

	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(detailsService);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration removing) throws Exception {
		return removing.getAuthenticationManager();
	}

}
