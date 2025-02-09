package com.piseth.java.school.phoneshopenight.service;

import java.util.List;

import com.piseth.java.school.phoneshopenight.entity.Model;

public interface ModelService {
	Model save(Model model);
	List<Model> getByBrand(Long brandId);
	List<Model>getModelByBrandId(Long id);
	Model getById(Long id);
}
