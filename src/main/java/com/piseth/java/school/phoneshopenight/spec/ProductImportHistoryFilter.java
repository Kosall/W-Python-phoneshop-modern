package com.piseth.java.school.phoneshopenight.spec;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data

public class ProductImportHistoryFilter {
	private LocalDateTime startDate;
	private LocalDateTime endDate;

}
