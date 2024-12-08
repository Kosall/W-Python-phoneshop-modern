package com.piseth.java.school.phoneshopenight.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.piseth.java.school.phoneshopenight.entity.Colour;

public interface ColourService {
	Colour build(Colour colour);
	List<Colour>getColour();
	Page<Colour>getColours(Map<String, String>params);
	void delete(Long id);
	Colour getById(Long id);

}
