package com.piseth.java.school.phoneshopenight.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piseth.java.school.phoneshopenight.dto.ColourDTO;
import com.piseth.java.school.phoneshopenight.dto.PageDTO;
import com.piseth.java.school.phoneshopenight.entity.Colour;
import com.piseth.java.school.phoneshopenight.mapper.ColourMapper;
import com.piseth.java.school.phoneshopenight.service.ColourService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("colours")
@RequiredArgsConstructor
public class ColourController {
	@Autowired
	private final ColourService colourService;
	@PostMapping
	
	public ResponseEntity<?> create(@RequestBody ColourDTO colourDTO){
		Colour colour = ColourMapper.INSTANTIATION.toColour(colourDTO);
		colour=colourService.build(colour);
		return ResponseEntity.ok(ColourMapper.INSTANTIATION.toColourDTO(colour));
		
	}
	
	@GetMapping("col")
	public ResponseEntity<?>getColour(){
		List<Colour> colour = colourService.getColour();
		List<ColourDTO> dto=new ArrayList<>();
		for(Colour color:colour) {
			ColourDTO colourDTO = ColourMapper.INSTANTIATION.toColourDTO(color);
			dto.add(colourDTO);
		}
		return ResponseEntity.ok(dto);
	}
	@GetMapping
	public ResponseEntity<?>getColours(@RequestParam Map<String, String>params ){
		Page<Colour> colours = colourService.getColours(params);
		PageDTO dto=new PageDTO(colours);
		return ResponseEntity.ok(dto);
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<?>delete(@PathVariable Long id){
		colourService.delete(id);
		return ResponseEntity.ok().build();
	}
	

}
