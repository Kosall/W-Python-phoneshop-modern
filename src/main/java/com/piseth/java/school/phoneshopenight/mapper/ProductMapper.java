package com.piseth.java.school.phoneshopenight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.piseth.java.school.phoneshopenight.dto.ProductDTO;
import com.piseth.java.school.phoneshopenight.entity.Product;
import com.piseth.java.school.phoneshopenight.service.ColourService;
import com.piseth.java.school.phoneshopenight.service.ModelService;

@Mapper(componentModel = "spring",uses = {ModelService.class,ColourService.class})
public interface ProductMapper {
	@Mapping(target = "model",source = "modelId")
	@Mapping(target = "colour",source = "colourId")
	Product toProduct(ProductDTO productDTO);

}
