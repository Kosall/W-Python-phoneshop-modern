package com.piseth.java.school.phoneshopenight.service;

import java.time.LocalDateTime;
import java.util.List;

import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.dto.report.ExpenseReportDTO;
import com.piseth.java.school.phoneshopenight.projection.ProductSoldService;

public interface ReportService {
	List<ProductSoldService> getProductSoldService(LocalDateTime startDate,LocalDateTime endDate);
	List<ProductReportDTO> getReportProduct(LocalDateTime startDate,LocalDateTime endDate);
	List<ExpenseReportDTO> getReportAsExpense(LocalDateTime startDate,LocalDateTime endDate);
	

}
