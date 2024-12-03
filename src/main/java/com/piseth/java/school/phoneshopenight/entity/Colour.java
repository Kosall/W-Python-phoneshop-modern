package com.piseth.java.school.phoneshopenight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="colours")
public class Colour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="colour_id")
	private Long id;
	@Column(name="colour_name")
	private String name;

}
