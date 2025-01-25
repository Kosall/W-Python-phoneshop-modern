package com.piseth.java.school.phoneshopenight.config.based.secure;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import  static com.piseth.java.school.phoneshopenight.config.based.secure.Permission.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
@AllArgsConstructor(access = AccessLevel.PRIVATE)


public enum RoleEnum {
	ADMIN(Set.of(BRAND_WRITE,BRAND_READ,MODEL_WRITE,MODEL_READ)),
	SALE(Set.of(MODEL_READ,BRAND_READ));
	
private Set<Permission> permissions;
public Set<SimpleGrantedAuthority> getAuthorities(){
	 Set<SimpleGrantedAuthority> collect = this.permissions.stream()
		.map(permissions->new SimpleGrantedAuthority(permissions.getDescription()))
		.collect(Collectors.toSet());
	SimpleGrantedAuthority role= new SimpleGrantedAuthority("ROLE_"+this.name());
	collect.add(role);
	System.out.println(collect);
	
	 return collect;
	
}
}
