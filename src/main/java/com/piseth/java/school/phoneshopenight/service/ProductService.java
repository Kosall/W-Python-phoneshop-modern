package com.piseth.java.school.phoneshopenight.service;

import java.math.BigDecimal;

import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;

public interface ProductService {
	Product create(Product product);
	void importProduct(ProductImportDTO importDTO);
	Product getById(Long id);
	void setSalePrice(Long productId,BigDecimal price);
	void validateStocking(Long productId,Integer quantity);

}
