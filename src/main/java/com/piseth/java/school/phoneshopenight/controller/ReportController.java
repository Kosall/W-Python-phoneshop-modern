package com.piseth.java.school.phoneshopenight.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.dto.report.ExpenseReportDTO;
import com.piseth.java.school.phoneshopenight.projection.ProductSoldService;
import com.piseth.java.school.phoneshopenight.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor

public class ReportController {
	private final ReportService reportService;

	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> soldProduct( @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("startDate")  LocalDateTime startDate,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("endDate") LocalDateTime endDate) {
		List<ProductSoldService> products = reportService.getProductSoldService(startDate, endDate);

		return ResponseEntity.ok(products);
	}
	@GetMapping("2/{startDate}/{endDate}")
	public ResponseEntity<?> soldProductV2( @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("startDate")  LocalDateTime startDate,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("endDate") LocalDateTime endDate) {
		List<ProductReportDTO> products = reportService.getReportProduct(startDate, endDate);

		return ResponseEntity.ok(products);
	}
	@GetMapping("{startDate}/{endDate}/expense")
	public ResponseEntity<?> expenseReport( @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("startDate")  LocalDateTime startDate,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") @PathVariable("endDate") LocalDateTime endDate) {
		 List<ExpenseReportDTO> products = reportService.getReportAsExpense(startDate, endDate);

		return ResponseEntity.ok(products);
	}

}
