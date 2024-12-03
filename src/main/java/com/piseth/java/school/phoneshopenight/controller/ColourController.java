package com.piseth.java.school.phoneshopenight.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.dto.ColourDTO;
import com.piseth.java.school.phoneshopenight.entity.Colour;
import com.piseth.java.school.phoneshopenight.mapper.ColourMapper;
import com.piseth.java.school.phoneshopenight.service.ColourService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("colours")
@RequiredArgsConstructor
public class ColourController {
	private final ColourService colourService;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ColourDTO colourDTO){
		Colour colour = ColourMapper.INSTANTIATION.toColour(colourDTO);
		colour=colourService.build(colour);
		return ResponseEntity.ok(ColourMapper.INSTANTIATION.toColourDTO(colour));
		
	}

}
