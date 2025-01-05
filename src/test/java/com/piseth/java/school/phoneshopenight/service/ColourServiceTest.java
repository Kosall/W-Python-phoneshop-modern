package com.piseth.java.school.phoneshopenight.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piseth.java.school.phoneshopenight.entity.Colour;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.ColourRespository;
import com.piseth.java.school.phoneshopenight.service.impl.ColourServiceImpl;

import javafx.beans.binding.When;

@ExtendWith(MockitoExtension.class)

public class ColourServiceTest {
	@Mock
	private ColourRespository colourRespository;
	private ColourService colourService;
	@BeforeEach
	public void setUp() {
		colourService= new ColourServiceImpl(colourRespository);
	}
	@Test
	public void testBuild() {
		Colour colour=new Colour();
		colour.setName("Red");
		colourService.build(colour);
		verify(colourRespository,times(1)).save(colour);
		
	}
	@Test
	public void testGetColourByIdHappyPart() {
		
		Colour colours=new Colour();
		colours.setId(1l);
		colours.setName("Red");
		
		when(colourRespository.findById(1l)).thenReturn(Optional.of(colours));
		
		
		Colour colourReturn = colourService.getById(1l);
		assertEquals(1l,  colourReturn.getId());
		assertEquals("Red", colourReturn.getName());
		
	}
	
	@Test
	void testGetByIdThrowSadPart() {
		Colour colour=new Colour();
		colour.setId(2l);
		colour.setName("Brown");
		when(colourRespository.findById(1l)).thenReturn(Optional.empty());
		assertThatThrownBy(()->colourService.getById(1l))
		.isInstanceOf(ResourceNotFoundException.class)
		.hasMessage(String.format("%s With id = %d not found","Colour",1 ));
	}
	
	
	

}
