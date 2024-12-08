package com.piseth.java.school.phoneshopenight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="colours")
public class Colour {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "mySeqGen")
	@SequenceGenerator(name="mySeqGen",sequenceName = "colours_colour_id_seq",initialValue = 1,allocationSize = 1)
	@Column(name="colour_id",updatable=false,nullable = false)
	private Long id;
	@Column(name="colour_name")
	private String name;

}
