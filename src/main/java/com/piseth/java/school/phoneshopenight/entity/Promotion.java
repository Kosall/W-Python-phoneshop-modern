package com.piseth.java.school.phoneshopenight.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Promotion {
	private  LocalDateTime currentDate;
	private LocalDateTime endDate;
	private double promotionPercent=0.05;
	private Product product;
	

}
