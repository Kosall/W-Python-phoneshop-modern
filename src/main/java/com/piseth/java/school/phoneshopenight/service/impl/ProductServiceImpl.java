package com.piseth.java.school.phoneshopenight.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.piseth.java.school.phoneshopenight.service.ColourService;
import com.piseth.java.school.phoneshopenight.service.ModelService;
import com.piseth.java.school.phoneshopenight.service.ProductService;

import liquibase.repackaged.org.apache.commons.collections4.map.HashedMap;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository importHistories;
	private final ProductMapper mapper;
	private final ModelService modelService;
	private final ColourService colourService;
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

	/*@Override
	public Map<Integer, String> upload(MultipartFile file) {
		Map<Integer, String> mapping = new HashedMap<>();

		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet("product");
			Iterator<Row> iterator = sheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Integer rowNumber = 0;
				try {
					Integer index = 0;
					Row row = iterator.next();
					Cell cellNo = row.getCell(index++);
					rowNumber = (int) cellNo.getNumericCellValue();
					Cell cellModelId = row.getCell(index++);
					Long modelId = (long) cellModelId.getNumericCellValue();

					Cell cellColourId = row.getCell(index++);
					Long colourId = (long) cellColourId.getNumericCellValue();

					Cell cellUnit = row.getCell(index++);
					Integer importUnit = (int) cellUnit.getNumericCellValue();
					if (importUnit < 1) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater than 0!!");
					}

					Cell cellUnitPrice = row.getCell(index++);
					Integer importPrice = (int) cellUnitPrice.getNumericCellValue();
					if (importPrice < 1) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "Unit price must be greater than 0!!");
					}

					Cell cellDate = row.getCell(index++);
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
				} catch (ApiException e) {
					mapping.put(rowNumber, e.getMessage());
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping;

	}*/
	/*@Override
	public Map<Integer, String> upload(MultipartFile file){
		Map<Integer, String> mapping = new HashedMap<>();
		
		Iterator<Row> rowIterator = getRowIterator(file);
		rowIterator.next();
		Integer rowNumber=0;
		while (rowIterator.hasNext()) {
			
			try {
				rowNumber++;
				Integer index = 0;
				 Row row = rowIterator.next();
				Cell cellNumber = row.getCell(index++);
				rowNumber = (int) cellNumber.getNumericCellValue();

				Cell cellModelId = row.getCell(index++);
				Long modelId = (long) cellModelId.getNumericCellValue();

				Cell cellColourId = row.getCell(index++);
				Long colourId = (long) cellColourId.getNumericCellValue();

				Cell cellUnit = row.getCell(index++);
				Integer importUnit = (int) cellUnit.getNumericCellValue();
				if (importUnit < 1) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater than 0!!");
				}
				Cell cellUnitPrice = row.getCell(index++);
				Integer importPrice = (int) cellUnitPrice.getNumericCellValue();
				if (importPrice < 1) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "Unit price must be greater than 0!!");
				}
				Cell cellDate = row.getCell(index++);
				LocalDateTime importDate = cellDate.getLocalDateTimeCellValue();
				
				Product product = getByModelIdAndColourId(modelId, colourId);

				Integer availabeUnit = 0;
				if (product.getAvalabeUnit() > 0) {
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
				
				
			} catch (Exception e) {
			
				mapping.put(rowNumber, e.getMessage());
			}
		}
		
		return mapping;
	}
	
//	private CellValue getCellValue(MultipartFile file) {
//		CellValue[] cellValues;
//		Cell cell = getCell(file);
//		Integer numericCellValue = (int) cell.getNumericCellValue();
//	}
//	
//	private Cell getCell(MultipartFile file) {
//		Row row = getRow(file);
//		Integer index=0;
//		Cell cell = row.getCell(index++);
//		return cell;
//		
//	}
//
//	private Row getRow(MultipartFile file) {
//		Iterator<Row> rowIterator = getRowIterator(file);
//		rowIterator.next();
//		while(rowIterator.hasNext()) {
//			Row row = rowIterator.next();
//			return row;
//		}
//		return null;
//	}

	private Iterator<Row> getRowIterator(MultipartFile file) {
		Sheet sheet = getSheet(file);
		Iterator<Row> rowIterator = sheet.iterator();

		return rowIterator;

	}

	private Sheet getSheet(MultipartFile file) {
			Sheet sheet=null;
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			sheet=workbook.getSheet("product");
			return sheet;
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	
		return null;

	}*/

	@Override
	public Product getByModelIdAndColourId(Long modelId, Long colourId) {
		modelService.getById(modelId);
		colourService.getById(colourId);
		String letter = "[Product] with model id :%s && colour id :%s not found!!";
		return productRepository.findByModelIdAndColourId(modelId, colourId)
				.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, letter.formatted(modelId, colourId)));
	}

}
