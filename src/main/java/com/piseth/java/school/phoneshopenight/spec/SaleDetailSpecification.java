package com.piseth.java.school.phoneshopenight.spec;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phoneshopenight.entity.Sale;
import com.piseth.java.school.phoneshopenight.entity.SaleDetail;

import lombok.AllArgsConstructor;
@AllArgsConstructor

public class SaleDetailSpecification implements Specification<SaleDetail> {
	private SaleDetailFilter filter;

	@Override
	public Predicate toPredicate(Root<SaleDetail> sd, CriteriaQuery<?> query, CriteriaBuilder cba) {
		// TODO Auto-generated method stub
		List<Predicate> predicates = new ArrayList<>();
		Join<SaleDetail, Sale> sale = sd.join("sale");
		if (Objects.nonNull(filter.getStartDate())) {

			cba.greaterThanOrEqualTo(sale.get("saleDate"), filter.getStartDate());
		}
		if (Objects.nonNull(filter.getEndDate())) {

			cba.lessThanOrEqualTo(sale.get("saleDate"), filter.getEndDate());
		}
		
		
		return cba.and(predicates.toArray(Predicate[]::new));

		
	}

}
