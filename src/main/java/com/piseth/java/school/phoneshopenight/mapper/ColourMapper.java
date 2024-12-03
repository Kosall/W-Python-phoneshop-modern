package com.piseth.java.school.phoneshopenight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.piseth.java.school.phoneshopenight.dto.ColourDTO;
import com.piseth.java.school.phoneshopenight.entity.Colour;

@Mapper
public interface ColourMapper {
	ColourMapper INSTANTIATION =Mappers.getMapper(ColourMapper.class);
	Colour toColour(ColourDTO colourDTO);
	ColourDTO toColourDTO(Colour colour);

}
