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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;

import lombok.Builder;
import lombok.Data;
@Builder
@Entity
@Data
@Table(name="products",uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","colour_id"})})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long id;
	@Column(name="product_name",unique = true)
	private String name;
	@Column(name="unit")
	private int avalabeUnit;
	@ManyToOne
	@JoinColumn(name="model_id")
	private Model model;
	@ManyToOne
	@JoinColumn(name="colour_id")
	private Colour colour;
	@Column(name="sale_price")
	@DecimalMin(value="0.00001",message = "Price must be greater than 0")
	private BigDecimal salePrice;

}
