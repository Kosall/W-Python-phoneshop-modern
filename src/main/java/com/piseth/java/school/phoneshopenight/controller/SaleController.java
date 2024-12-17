package com.piseth.java.school.phoneshopenight.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("sales")
@RequiredArgsConstructor
public class SaleController {
	private final SaleService saleService;
	@PostMapping
	public ResponseEntity<?> setSell(@RequestBody SaleDTO saleDTO){
		saleService.seller(saleDTO);
		return ResponseEntity.ok().build();
		
	}

}
