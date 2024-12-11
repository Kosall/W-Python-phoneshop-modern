package com.piseth.java.school.phoneshopenight.entity;

import java.math.BigDecimal;
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
@Table(name="import_histories")
public class ProductImportHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="history_id")
	private Long id;
	@Column(name="import_unit")
	private Integer importUnit;
	@Column(name="unit_price")
	private BigDecimal importPrice;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	@Column(name="import_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private LocalDateTime importDate;
}
