package com.piseth.java.school.phoneshopenight.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.graphbuilder.curve.MultiPath;
import com.piseth.java.school.phoneshopenight.dto.PriceDTO;
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
	@PostMapping("{id}/salePrice")
	
	public ResponseEntity<?>setSalePrice(@PathVariable("id") Long producdId,@RequestBody PriceDTO priceDTO)
	{
		productService.setSalePrice(producdId, priceDTO.getPrice());
		return ResponseEntity.ok().build();
		
	}
	@PostMapping("upload")
	public ResponseEntity<?>upload(@RequestParam("file") MultipartFile filer){
		productService.upload(filer);
		return ResponseEntity.ok().build();
	}

}
