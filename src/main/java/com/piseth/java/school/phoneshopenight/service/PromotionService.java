package com.piseth.java.school.phoneshopenight.service;

import java.time.LocalDateTime;

import com.piseth.java.school.phoneshopenight.entity.Promotion;
import com.piseth.java.school.phoneshopenight.entity.Sale;

public interface PromotionService {
	void getPromotion(Sale sale,Promotion promotion);
}
