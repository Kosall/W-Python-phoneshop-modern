package com.piseth.java.school.phoneshopenight.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SaleDTO {
	private List<ProductSellDTO> productions;
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime saleDate;

}
