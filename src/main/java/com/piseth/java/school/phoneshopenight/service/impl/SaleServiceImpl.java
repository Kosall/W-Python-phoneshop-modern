package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.dto.ProductSellDTO;
import com.piseth.java.school.phoneshopenight.dto.SaleDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
import com.piseth.java.school.phoneshopenight.exception.ApiException;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.ProductRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshopenight.repository.SaleRepository;
import com.piseth.java.school.phoneshopenight.service.ProductService;
import com.piseth.java.school.phoneshopenight.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository repository;
	private final SaleDetailRepository detailRepository;
	

	@Override
	public void seller(SaleDTO saleDTO) {
		// validate(saleDTO);
		getProductId(saleDTO);
		getListOfDTO(saleDTO);
		setSaleDetail(saleDTO);

	}
	/*
	 * private void saveSale(SaleDTO saleDTO) {
	 * 
	 * }
	 */

	private List<Long> getProductId(SaleDTO saleDTO) {
		List<Long> productId = saleDTO.getProductions().stream().map(ProductSellDTO::getProductId).toList();
		return productId;
	}

	private void getListOfDTO(SaleDTO saleDTO) {
//		List<Long> productId = getProductId(saleDTO);
//		List<Product> list = productId.stream().map(brs->productService.getById(brs)).toList();
//		Map<Long, Product> collect = list.stream().collect(Collectors.toMap(Product::getId,Function.identity()));
		saleDTO.getProductions().stream().forEach(br -> {
			Product product = productService.getById(br.getProductId());
			if (product.getAvalabeUnit() < br.getQuantity()) {
				throw new ApiException(HttpStatus.BAD_REQUEST,
						"Product named:[%s] in stock is not enough!!".formatted(product.getName()));
			}
		});
	}

//	private void validate(SaleDTO saleDTO) {
//		List<Long> productId = saleDTO.getProductions().stream().map(ProductSellDTO::getProductId).toList();
//		//validation
//		productId.forEach(productService::getById);
//		List<Product> listOfProduct = productRepository.findAllById(productId);
//		Map<Long, Product> productionMap = listOfProduct.stream().collect(Collectors.toMap(Product::getId,Function.identity()));
//		saleDTO.getProductions().forEach(ps->{
//			Product production =  productService.getById(ps.getProductId());
//			if(production.getAvalabeUnit()<ps.getQuantity()) {
//				throw new ApiException(HttpStatus.BAD_REQUEST, "Product named:[%s] in stock is not enough!!".formatted(production.getName()));
//			}
//		});
//		Sale sale=new Sale();
//		sale.setSaleDate(saleDTO.getSaleDate());
//		repository.save(sale);
//		saleDTO.getProductions().forEach(pr->{
//			Product product = productionMap.get(pr.getProductId());
//			SaleDetail saleDetail=new SaleDetail();
//			saleDetail.setAmount( product.getSalePrice());
//			saleDetail.setProduct(product);
//			saleDetail.setSale(sale);
//			saleDetail.setUnit(pr.getQuantity());
//			detailRepository.save(saleDetail);
//			
//		});
//			
//		}
	private void setSaleDetail(SaleDTO saleDTO) {
//		List<Long> productId = saleDTO.getProductions().stream().map(ProductSellDTO::getProductId).toList();
//		// validation
//		productId.forEach(productService::getById);
//		List<Product> listOfProduct = productRepository.findAllById(productId);
//		Map<Long, Product> productionMap = listOfProduct.stream()
//				.collect(Collectors.toMap(Product::getId, Function.identity()));
//		Sale sale = new Sale();
//		sale.setSaleDate(saleDTO.getSaleDate());
//		repository.save(sale);
		 getIdOfProduct(saleDTO);
		 getListOfProduct(saleDTO);
		getProductAndSaleDetails(saleDTO);
		
//		saleDTO.getProductions().forEach(pr -> {
//			Product product = productMap.get(pr.getProductId());
//			SaleDetail saleDetail = new SaleDetail();
//			saleDetail.setAmount(product.getSalePrice());
//			saleDetail.setProduct(product);
//			saleDetail.setSale(sale);
//			saleDetail.setUnit(pr.getQuantity());
//			detailRepository.save(saleDetail);
//			Integer newIn = product.getAvalabeUnit() - pr.getQuantity();
//			product.setAvalabeUnit(newIn);
//			productRepository.save(product);
//			
//		});
	}
	private void getProductAndSaleDetails(SaleDTO saleDTO) {
		 Map<Long, Product> productMap = getMapOfProduct(saleDTO);
		 Sale sale = getSale(saleDTO);
		saleDTO.getProductions().forEach(pr -> {
			Product product = productMap.get(pr.getProductId());
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setAmount(product.getSalePrice());
			saleDetail.setProduct(product);
			saleDetail.setSale(sale);
			saleDetail.setUnit(pr.getQuantity());
			detailRepository.save(saleDetail);
			Integer newIn = product.getAvalabeUnit() - pr.getQuantity();
			product.setAvalabeUnit(newIn);
			productRepository.save(product);
			
		});
	}
	
	

	private Sale getSale(SaleDTO saleDTO) {
		Sale sale=new Sale();
		sale.setSaleDate(saleDTO.getSaleDate());
		
		return repository.save(sale);
		
	}
	
	private Map<Long, Product> getMapOfProduct(SaleDTO saleDTO){
		
		return getListOfProduct(saleDTO)
				.stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));
		
	}
	private List<Product> getListOfProduct(SaleDTO saleDTO){
		List<Long> idOfProduct = getIdOfProduct(saleDTO);
		List<Product> list = productRepository.findAllById(idOfProduct);
		return list;
	}
	private List<Long> getIdOfProduct(SaleDTO saleDTO) {
		List<Long> productId = saleDTO.getProductions().stream().map(ProductSellDTO::getProductId).toList();
		return productId;
	}

	@Override
	public void cancelSale(Long saleId) {
		// TODO Auto-generated method stub
		Sale sale = getById(saleId);
		sale.setActive(false);
		repository.save(sale);
		List<SaleDetail> saleDetail = detailRepository.findBySaleId(saleId);
		List<Long> productIds = saleDetail.stream().map(sd->sd.getProduct().getId()).toList();
		List<Product> products = productRepository.findAllById(productIds);
		 Map<Long, Product> collectOfProducts = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
		
		saleDetail.forEach(sd->{
			Product product = collectOfProducts.get(sd.getProduct().getId());
			product.setAvalabeUnit(product.getAvalabeUnit()+sd.getUnit());
			
			
			productRepository.save(product);
		});
		
		
		
	}

	@Override
	public Sale getById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Sale", id));
	}

	@Override
	public void delete(Sale sale) {
		detailRepository.delete(null);
		
		repository.delete(sale);
		
		
	}
}

