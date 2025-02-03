package com.piseth.java.school.phoneshopenight.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import com.piseth.java.school.phoneshopenight.config.based.secure.RoleEnum;

import lombok.Data;
import net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.Eager;

@Entity
@Table(name="users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private  String firstname;
	private String lastname;
	private String username;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

}
