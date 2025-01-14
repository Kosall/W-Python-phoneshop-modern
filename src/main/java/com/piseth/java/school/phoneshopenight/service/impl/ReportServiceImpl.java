package com.piseth.java.school.phoneshopenight.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductReportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import com.piseth.java.school.phoneshopenight.projection.ProductSoldService;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ReportService;
import com.piseth.java.school.phoneshopenight.spec.SaleDetailFilter;
import com.piseth.java.school.phoneshopenight.spec.SaleDetailSpecification;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private final SaleRepository saleRepository;
	private final SaleDetailRepository detailRepository;
	private final ProductRepository productRepository;

	@Override
	public List<ProductSoldService> getProductSoldService(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return saleRepository.findProductSoldService(startDate, endDate);
	}

	@Override
	public List<ProductReportDTO> getReportProduct(LocalDateTime startDate, LocalDateTime endDate) {
		List<ProductReportDTO> lists=new ArrayList<>();
		SaleDetailFilter detailFilter=new SaleDetailFilter();
		detailFilter.setStartDate(startDate);
		detailFilter.setEndDate(endDate);
		Specification<SaleDetail> sales=new SaleDetailSpecification(detailFilter);
		 List<SaleDetail> findAll = detailRepository.findAll(sales);
		  List<Long> listOfProductId = findAll.stream().map(sd->sd.getProduct().getId()).toList();
		  Map<Long, Product> productMapping = productRepository.findAllById(listOfProductId)
		  .stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		 
		 
		 Map<Product, List<SaleDetail>> saleDetailMapping = findAll.stream()
				 .collect(Collectors.groupingBy(SaleDetail::getProduct));
		 for(var entry:saleDetailMapping .entrySet()) {
			 Product product = productMapping.get(entry.getKey().getId());
			 ProductReportDTO productReportDTO=new ProductReportDTO();
			 productReportDTO.setProductId(product.getId());
			 productReportDTO.setProductName(product.getName());
			 List<SaleDetail> saleDetailList = entry.getValue();
			 Integer reduce = saleDetailList.stream().map(SaleDetail::getUnit)
			 .reduce(0,(a,b)->a+b);
			 productReportDTO.setUnit(reduce);
			// First way Double totalAmount = saleDetailList.stream().map(sd->(sd.getUnit()*sd.getAmount().doubleValue())).reduce(0.0,(a,b)->a+b)
					 ;
			// productReportDTO.setUnit(reduce);
					 double sum = saleDetailList.stream().mapToDouble(sd->(sd.getUnit()*sd.getAmount().doubleValue())).sum();
					 BigDecimal sumAmount= BigDecimal.valueOf(sum);
			productReportDTO.setTotalAmount(sumAmount);
			lists.add(productReportDTO);
			 
		 }
		
		return lists;
	}
	

}
