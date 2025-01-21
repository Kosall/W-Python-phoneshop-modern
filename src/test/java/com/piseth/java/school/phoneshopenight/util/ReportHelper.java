package com.piseth.java.school.phoneshopenight.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;

public class ReportHelper {
	
	private static Product product1=Product.builder()
			.id(1l)
			.name("iPhone 14 Pro max")
			.build();
	private static Product product2=Product.builder()
			.id(2l)
			.name("iPhone 16")
			.build();
	private static Product product3=Product.builder()
			.id(3l)
			.name("Samsung Galaxy s18")
			.build();
	
	public static List<Product>getProduct(){
		return List.of(product1,product2,product3);
	}
	public static List<ProductImportHistory> getProductHistories(){
		ProductImportHistory h1= ProductImportHistory.builder()
				.product(product1)
				.importUnit(6)
				.importDate(LocalDateTime.of(2024, 02, 27, 15, 31))
				.importPrice(BigDecimal.valueOf(1300))
				.build();
		ProductImportHistory h2= ProductImportHistory.builder()
				.product(product2)
				.importUnit(26)
				.importDate(LocalDateTime.of(2024, 02, 28, 8, 15))
				.importPrice(BigDecimal.valueOf(1600))
				.build();
		ProductImportHistory h3= ProductImportHistory.builder()
				.product(product3)
				.importUnit(7)
				.importDate(LocalDateTime.of(2024, 03, 8, 14, 15))
				.importPrice(BigDecimal.valueOf(2000))
				.build();
		return List.of(h1,h2);
	}

}
