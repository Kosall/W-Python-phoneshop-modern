package com.piseth.java.school.phoneshopenight.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piseth.java.school.phoneshopenight.dto.report.ExpenseReportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.impl.ReportServiceImpl;
import com.piseth.java.school.phoneshopenight.spec.ImportHistorySpecification;
import com.piseth.java.school.phoneshopenight.util.ReportHelper;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
	private ReportService reportService;
	@Mock
	private  SaleRepository saleRepository;
	@Mock
	private  SaleDetailRepository detailRepository;
	@Mock
	private  ProductRepository productRepository;
	@Mock
	private  ProductImportHistoryRepository historyRepository;
	@BeforeEach
	void setUp() {
		reportService=new ReportServiceImpl(saleRepository, detailRepository, productRepository, historyRepository);

	}
	@Test
	public void getReportAsExpense() {
		//GIVEN
		List<ProductImportHistory> listImports=ReportHelper.getProductHistories();
		List<Product>p1=ReportHelper.getProduct();
		//WHEN
		when(historyRepository.findAll(Mockito.any(ImportHistorySpecification.class))).thenReturn(listImports);
		when(productRepository.findAllById(anySet())).thenReturn(p1);
		List<ExpenseReportDTO> reportAsExpense = reportService.getReportAsExpense(LocalDateTime.now().minusYears(1), LocalDateTime.now());
		//THEN
		ExpenseReportDTO expense1 = reportAsExpense.get(0);
		assertEquals(2,reportAsExpense.size());
		assertEquals(1l, expense1.getProductId());
		assertEquals("iPhone 14 Pro max", expense1.getProductName());
		assertEquals( expense1.getTotalUnit(), 6);
		assertEquals( expense1.getTotalAmount().doubleValue(),7800.00);
		
		
		
	}

}
