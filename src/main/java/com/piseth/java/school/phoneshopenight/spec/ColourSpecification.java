package com.piseth.java.school.phoneshopenight.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.piseth.java.school.phoneshopenight.entity.Colour;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ColourSpecification implements Specification<Colour>{
	@Autowired
	private final ColourFilter colourFilter;
	List<Predicate>predicates=new ArrayList<>();
	@Override
	public Predicate toPredicate(Root<Colour> colour, CriteriaQuery<?> qr, CriteriaBuilder cb) {
		if(colourFilter.getName()!=null) {
			Predicate name=cb.like(cb.upper(colour.get("name")),"%" + colourFilter.getName().toUpperCase()+"%");
			predicates.add(name);
		}
		if(colourFilter.getId()!=null) {
			Predicate id=colour.get("id").in(colourFilter.getId());
			predicates.add(id);
		}
		return cb.and(predicates.toArray(Predicate[]::new));
	}

}
