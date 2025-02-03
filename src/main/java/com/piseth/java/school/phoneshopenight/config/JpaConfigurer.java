package com.piseth.java.school.phoneshopenight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfigurer {
	@Bean
	public AuditorAware<String>getAuditorAware(){
		
		return new AuditorAwareImpl();
	}

}
