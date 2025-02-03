package com.piseth.java.school.phoneshopenight.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements  AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
			//SecurityContextHolder.getContext().getAuthentication().getName();
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
	}

}
