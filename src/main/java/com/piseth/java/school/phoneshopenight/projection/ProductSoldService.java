package com.piseth.java.school.phoneshopenight.projection;

import java.math.BigDecimal;

public interface ProductSoldService {
	Long getProductId();
	String getProductName();
	Integer getUnit();
	BigDecimal getTotalAmount();

}
