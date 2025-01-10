package com.piseth.java.school.phoneshopenight.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.projection.ProductSoldService;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ReportService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private final SaleRepository saleRepository;

	@Override
	public List<ProductSoldService> getProductSoldService(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return saleRepository.findProductSoldService(startDate, endDate);
	}
	

}
