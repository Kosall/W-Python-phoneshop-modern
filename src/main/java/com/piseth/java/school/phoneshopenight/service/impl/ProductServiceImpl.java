package com.piseth.java.school.phoneshopenight.service.impl;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.mapper.ProductMapper;
import com.piseth.java.school.phoneshopenight.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository productImportHistoryRepository;
	private final ProductMapper mapper;

	@Override
	public Product create(Product product) {
		String name="%s, %s".formatted(product.getModel().getName(),product.getColour().getName());
		product.setName(name);
		return productRepository.save(product);
	}

	

	@Override
	public Product getById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product", id));
	}
	@Override
	public void importProduct(ProductImportDTO importDTO) {
		// TODO Auto-generated method stub
		Product product = getById(importDTO.getProductId());
		Integer availabeUnit=product.getAvalabeUnit()+importDTO.getImportUnit();
		product.setAvalabeUnit(availabeUnit);
		productRepository.save(product);
		ProductImportHistory importHistory = mapper.toProductImportHistory(importDTO, product);
		productImportHistoryRepository.save(importHistory);
		
	}

}
