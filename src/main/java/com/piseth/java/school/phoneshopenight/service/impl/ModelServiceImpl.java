package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.entity.Model;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.BrandRepository;
import com.piseth.java.school.phoneshopenight.repository.ModelRepository;
import com.piseth.java.school.phoneshopenight.service.BrandService;
import com.piseth.java.school.phoneshopenight.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {
	private final ModelRepository modelRepository;
	private final BrandService brandService;

	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	}

	@Override
	public List<Model> getByBrand(Long brandId) {
		return modelRepository.findByBrandId(brandId);
	}

	@Override
	public List<Model> getModelByBrandId(Long id) {
		// TODO Auto-generated method stub
		brandService.getById(id);
		List<Model> list = modelRepository.findByBrandId(id);
		return list;
	}

	@Override
	public Model getById(Long id) {
		// TODO Auto-generated method stub
		return modelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Model", id));
	}

}
