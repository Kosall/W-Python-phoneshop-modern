package com.piseth.java.school.phoneshopenight.spec;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SaleDetailFilter {
	private LocalDateTime startDate;
	private LocalDateTime endDate;


}
