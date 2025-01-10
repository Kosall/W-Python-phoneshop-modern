package com.piseth.java.school.phoneshopenight.service;

import java.time.LocalDateTime;
import java.util.List;

import com.piseth.java.school.phoneshopenight.projection.ProductSoldService;

public interface ReportService {
	List<ProductSoldService> getProductSoldService(LocalDateTime startDate,LocalDateTime endDate);
	

}
