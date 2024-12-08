package com.piseth.java.school.phoneshopenight.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.entity.Colour;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.ColourRespository;
import com.piseth.java.school.phoneshopenight.service.ColourService;
import com.piseth.java.school.phoneshopenight.service.util.PageUtil;
import com.piseth.java.school.phoneshopenight.spec.ColourFilter;
import com.piseth.java.school.phoneshopenight.spec.ColourSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColourServiceImpl implements ColourService {
	@Autowired
	private final ColourRespository colourRespository;

	@Override
	public Colour build(Colour colour) {
		// TODO Auto-generated method stub
		return colourRespository.save(colour);
	}

	@Override
	public List<Colour> getColour() {
		// TODO Auto-generated method stub
		return colourRespository.findAll();
	}

	@Override
	public Page<Colour> getColours(Map<String, String> params) {
		ColourFilter colourFilter=new ColourFilter();
		if(params.containsKey("name")) {
			String name=params.get("name");
			colourFilter.setName(name);
		}
		if(params.containsKey("id")) {
			String id=params.get("id");
			colourFilter.setId(Long.parseLong(id));
			
		}
		int pageLimit=PageUtil.DEFAULT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		int pageNumber=PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		ColourSpecification colourSpecification=new ColourSpecification(colourFilter);
		Pageable pageable=PageUtil.getPageable(pageNumber, pageLimit);
		Page<Colour> page=colourRespository.findAll(colourSpecification,pageable);
		return page;
	}

	@Override
	public void delete(Long id) {
		Colour colour = getById(id);
		colourRespository.delete(colour);
		
	}

	@Override
	public Colour getById(Long id) {
		// TODO Auto-generated method stub
		return colourRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Colour", id));
	}

}
