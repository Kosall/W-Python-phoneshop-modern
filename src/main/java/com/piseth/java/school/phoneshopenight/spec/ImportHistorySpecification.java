package com.piseth.java.school.phoneshopenight.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phoneshopenight.entity.ProductImportHistory;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@AllArgsConstructor
public class ImportHistorySpecification implements Specification<ProductImportHistory>{
	private ProductImportHistoryFilter historyFilter;

	@Override
	public Predicate toPredicate(Root<ProductImportHistory> productImport, CriteriaQuery<?> query,
			CriteriaBuilder cba) {
		List<Predicate>predicates=new ArrayList<>();
		if(Objects.nonNull(historyFilter.getStartDate())) {
			cba.greaterThanOrEqualTo(productImport.get("importDate"), historyFilter.getStartDate());
		}
		if(Objects.nonNull(historyFilter.getEndDate())) {
			cba.greaterThanOrEqualTo(productImport.get("importDate"), historyFilter.getEndDate());
		}
		return cba.and(predicates.toArray(Predicate[]::new));
		
	}

}
