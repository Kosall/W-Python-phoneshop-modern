package com.piseth.java.school.phoneshopenight.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.dto.report.ExpenseReportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import com.piseth.java.school.phoneshopenight.projection.ProductSoldService;
import com.piseth.java.school.phoneshopenight.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ReportService;
import com.piseth.java.school.phoneshopenight.spec.ImportHistorySpecification;
import com.piseth.java.school.phoneshopenight.spec.ProductImportHistoryFilter;
import com.piseth.java.school.phoneshopenight.spec.SaleDetailFilter;
import com.piseth.java.school.phoneshopenight.spec.SaleDetailSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	private final SaleRepository saleRepository;
	private final SaleDetailRepository detailRepository;
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository historyRepository;

	@Override
	public List<ProductSoldService> getProductSoldService(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return saleRepository.findProductSoldService(startDate, endDate);
	}

	@Override
	public List<ProductReportDTO> getReportProduct(LocalDateTime startDate, LocalDateTime endDate) {
		List<ProductReportDTO> lists = new ArrayList<>();
		SaleDetailFilter detailFilter = new SaleDetailFilter();
		detailFilter.setStartDate(startDate);
		detailFilter.setEndDate(endDate);
		Specification<SaleDetail> sales = new SaleDetailSpecification(detailFilter);
		List<SaleDetail> findAll = detailRepository.findAll(sales);
		List<Long> listOfProductId = findAll.stream().map(sd -> sd.getProduct().getId()).toList();
		Map<Long, Product> productMapping = productRepository.findAllById(listOfProductId).stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		Map<Product, List<SaleDetail>> saleDetailMapping = findAll.stream()
				.collect(Collectors.groupingBy(SaleDetail::getProduct));
		for (var entry : saleDetailMapping.entrySet()) {
			Product product = productMapping.get(entry.getKey().getId());
			ProductReportDTO productReportDTO = new ProductReportDTO();
			productReportDTO.setProductId(product.getId());
			productReportDTO.setProductName(product.getName());
			List<SaleDetail> saleDetailList = entry.getValue();
			Integer reduce = saleDetailList.stream().map(SaleDetail::getUnit).reduce(0, (a, b) -> a + b);
			productReportDTO.setUnit(reduce);
			// First way Double totalAmount =
			// saleDetailList.stream().map(sd->(sd.getUnit()*sd.getAmount().doubleValue())).reduce(0.0,(a,b)->a+b)
			;
			// productReportDTO.setUnit(reduce);
			double sum = saleDetailList.stream().mapToDouble(sd -> (sd.getUnit() * sd.getAmount().doubleValue())).sum();
			BigDecimal sumAmount = BigDecimal.valueOf(sum);
			productReportDTO.setTotalAmount(sumAmount);
			lists.add(productReportDTO);

		}

		return lists;
	}

	@Override
	public List<ExpenseReportDTO> getReportAsExpense(LocalDateTime startDate, LocalDateTime endDate) {
		// historyRepository.findAll(null);
		ProductImportHistoryFilter filter = new ProductImportHistoryFilter();
		filter.setStartDate(startDate);
		filter.setEndDate(endDate);
		ImportHistorySpecification historySpecification = new ImportHistorySpecification(filter);
		List<ProductImportHistory> importing = historyRepository.findAll(historySpecification);
		Set<Long> listofProductIdSet = importing.stream().map(pr -> pr.getProduct().getId())
				.collect(Collectors.toSet());
		// Map<Product, List<ProductImportHistory>> collect =
		// importing.stream().collect(Collectors.groupingBy(pr->pr.getProduct()));
		Map<Long, Product> productMapper = productRepository.findAllById(listofProductIdSet).stream()
				.collect(Collectors.toMap(p -> p.getId(), p -> p));
		Map<Product, List<ProductImportHistory>> importMapper = importing.stream()
				.collect(Collectors.groupingBy(ProductImportHistory::getProduct));
		var expenseReportDTOs = new ArrayList<ExpenseReportDTO>();
		for (var entry : importMapper.entrySet()) {
			Product product = productMapper.get(entry.getKey().getId());
			var expenseDTO = new ExpenseReportDTO();
			expenseDTO.setProductId(product.getId());
			expenseDTO.setProductName(product.getName());
			List<ProductImportHistory> listOfHistories = entry.getValue();
			Integer reduce = listOfHistories.stream().map(ProductImportHistory::getImportUnit).reduce(0,
					(a, b) -> a + b);
			expenseDTO.setTotalUnit(reduce);
			double sum = listOfHistories.stream()
					.mapToDouble(im -> (im.getImportUnit() * im.getImportPrice().doubleValue())).sum();

			expenseDTO.setTotalAmount(BigDecimal.valueOf(sum));
			expenseReportDTOs.add(expenseDTO);
		}
		Collections.sort(expenseReportDTOs,(a,b)->(int)(a.getProductId()-b.getProductId()));
		return expenseReportDTOs;
	}

}
