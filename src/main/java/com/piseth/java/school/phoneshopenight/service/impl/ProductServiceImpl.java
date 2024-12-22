package com.piseth.java.school.phoneshopenight.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.piseth.java.school.phoneshopenight.dto.ProductImportDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;
import com.piseth.java.school.phoneshopenight.exception.ApiException;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.mapper.ProductMapper;
import com.piseth.java.school.phoneshopenight.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository importHistories;
	private final ProductMapper mapper;
	// private final ProductService service;

	@Override
	public Product create(Product product) {
		String name = "%s, %s".formatted(product.getModel().getName(), product.getColour().getName());
		product.setName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		// TODO Auto-generated method stub
		Product product = getById(importDTO.getProductId());
		Integer availabeUnit = product.getAvalabeUnit() + importDTO.getImportUnit();
		product.setAvalabeUnit(availabeUnit);
		productRepository.save(product);
		ProductImportHistory importHistory = mapper.toProductImportHistory(importDTO, product);
		importHistories.save(importHistory);

	}

	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getById(productId);
		product.setSalePrice(price);
		productRepository.save(product);

	}

	@Override
	public void validateStocking(Long productId, Integer quantity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void upload(MultipartFile file) {
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet("product");
			Iterator<Row> iterator = sheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				Cell cellModelId = row.getCell(0);
				Long modelId = (long) cellModelId.getNumericCellValue();

				Cell cellColourId = row.getCell(1);
				Long colourId = (long) cellColourId.getNumericCellValue();

				Cell cellUnit = row.getCell(2);
				Integer importUnit = (int) cellUnit.getNumericCellValue();

				Cell cellUnitPrice = row.getCell(3);
				Integer importPrice = (int) cellUnitPrice.getNumericCellValue();

				Cell cellDate = row.getCell(4);
				LocalDateTime importDate = cellDate.getLocalDateTimeCellValue();

				Product product = getByModelIdAndColourId(modelId, colourId);

				Integer availabeUnit = 0;
				if (product.getAvalabeUnit() >= 0) {
					availabeUnit = product.getAvalabeUnit();
				}
				product.setAvalabeUnit(availabeUnit + importUnit);
				productRepository.save(product);
				ProductImportHistory importHistory = new ProductImportHistory();
				importHistory.setImportDate(importDate);
				importHistory.setImportUnit(importUnit);
				importHistory.setImportPrice(BigDecimal.valueOf(importPrice));
				importHistory.setProduct(product);
				importHistories.save(importHistory);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Product getByModelIdAndColourId(Long modelId, Long colourId) {
		String letter = "[Product] with model id :%s && colour id :%s not found!!";
		return productRepository.findByModelIdAndColourId(modelId, colourId)
				.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, letter.formatted(modelId, colourId)));
	}

}
