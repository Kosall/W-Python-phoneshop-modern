package com.piseth.java.school.phoneshopenight.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.projection.ProductSoldService;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
	@Query(value = "select pr.product_id productId,pr.product_name productName,sum(sd.unit) unit,sum(sd.unit*sd.amount) totalAmount from sale_datails sd\r\n"
			+ "inner join sales s on s.sale_id=sd.sale_id\r\n"
			+ "inner join products pr on pr.product_id=sd.product_id\r\n"
			+ "\r\n"
			+ "where s.sale_date>=:startDate and s.sale_date<=:endDate\r\n"
			+ "group by pr.product_id,pr.product_name"
			,nativeQuery = true)
	
	List<ProductSoldService> findProductSoldService(LocalDateTime startDate,LocalDateTime endDate);

}
