package com.piseth.java.school.phoneshopenight.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.dto.ProductDTO;
import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.mapper.ProductMapper;
import com.piseth.java.school.phoneshopenight.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("productions")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	private final ProductMapper productMapper;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
		Product product = productMapper.toProduct(productDTO);
		 product=productService.create(product);
		return ResponseEntity.ok(product);
		
	}
	@PostMapping("import")
	public ResponseEntity<?> importing(@RequestBody @Valid ProductImportDTO productDTO){
		productService.importProduct(productDTO);
		return ResponseEntity.ok().build();
	}
	

}
