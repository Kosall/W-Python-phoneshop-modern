package com.piseth.java.school.phoneshopenight.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProductImportDTO {
	@NotNull(message = "Id mustn't be null")
	private Long productId;
	@Min(value = 1,message = "import unit must be greater than 0.0")
	private Integer importUnit;
	@DecimalMin(value = "0.000001",message = "price must be greater than 0.000001")
	private BigDecimal importPrice;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "importDate must not be null!!!")
	private LocalDateTime importDate;
	

}
