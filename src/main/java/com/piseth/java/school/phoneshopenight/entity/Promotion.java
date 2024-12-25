package com.piseth.java.school.phoneshopenight.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
@Data
@Entity
@Table(name="promotions")
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long promotionId;
	@Column(name = "promotion_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime promotionDate;
	@Column(name="percent")
	private double promotionPercent=0.05;
	@JoinColumn
	@ManyToOne
	@Column(name="sale_price",unique = true)
	private Product product;
	

}
