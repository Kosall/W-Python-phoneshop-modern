package com.piseth.java.school.phoneshopenight.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="sale_datails")
public class SaleDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sale_detail_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name="sale_id")
	private Sale sale;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	@JoinColumn(name="amount")
	private BigDecimal amount;
	private Integer unit;
}
