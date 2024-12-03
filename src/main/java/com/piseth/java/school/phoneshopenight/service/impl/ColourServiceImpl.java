package com.piseth.java.school.phoneshopenight.service.impl;

import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.entity.Colour;
import com.piseth.java.school.phoneshopenight.repository.ColourRespository;
import com.piseth.java.school.phoneshopenight.service.ColourService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColourServiceImpl implements ColourService {
	private final ColourRespository colourRespository;

	@Override
	public Colour build(Colour colour) {
		// TODO Auto-generated method stub
		return colourRespository.save(colour);
	}

}
