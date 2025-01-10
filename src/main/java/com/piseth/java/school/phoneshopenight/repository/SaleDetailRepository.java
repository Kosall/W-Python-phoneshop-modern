package com.piseth.java.school.phoneshopenight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;
@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
	
	List<SaleDetail> findBySaleId(Long id);

}
