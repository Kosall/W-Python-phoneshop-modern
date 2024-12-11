package com.piseth.java.school.phoneshopenight.service.impl;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public Product create(Product product) {
		String name="%s %s".formatted(product.getModel().getName(),product.getColour().getName());
		product.setName(name);
		return productRepository.save(product);
	}

}
