package com.piseth.java.school.phoneshopenight.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.entity.Promotion;
import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.service.PromotionService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
	private final ProductRepository  productRepository;
	
	

	@Override
	public  void getPromotion(Sale sale,Promotion promotion) {
		LocalDateTime promotionDate = promotion.getPromotionDate();
		LocalDateTime saleDate = sale.getSaleDate();
			String time1="2024-12-14T08:00:00";
			
		
			boolean before = saleDate.isBefore(promotionDate);
			if(before==true) {
				
			}

	}

}
