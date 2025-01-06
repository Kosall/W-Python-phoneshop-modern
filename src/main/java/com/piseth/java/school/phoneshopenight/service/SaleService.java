package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.entity.Sale;

public interface SaleService {
	void seller(SaleDTO saleDTO);
	void cancelSale(Long saleId);
	Sale getById(Long id);
	void delete(Sale sale);
}
