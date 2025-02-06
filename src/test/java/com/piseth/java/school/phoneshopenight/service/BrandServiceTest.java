package com.piseth.java.school.phoneshopenight.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piseth.java.school.phoneshopenight.entity.Brand;
import com.piseth.java.school.phoneshopenight.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshopenight.repository.BrandRepository;
import com.piseth.java.school.phoneshopenight.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

	@Mock
	private BrandRepository brandRepository;
	@Captor
	private ArgumentCaptor<Brand> brandCaptor;

	private BrandService brandService;

	@BeforeEach
	public void setUp() {
		brandService = new BrandServiceImpl(brandRepository);
	}
/*
	@Test
	public void testCreate() {
		// given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1);
		
		Brand brand2 = new Brand();
		brand.setName("Apple");

		// when
		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		//when(brandRepository.save(brand2)).thenReturn(brand);
		Brand brandReturn = brandService.create(new Brand());
		// then
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());

	}
	*/
	
	@Test
	public void testCreate() {
		// given
		Brand brand = new Brand();
		brand.setName("Apple");
		// when
		brandService.create(brand);
		// then
		verify(brandRepository, times(1)).save(brand);
		//verify(brandRepository, times(1)).delete(brand);
	}
	
	@Test
	public void testGetByIdSuccess() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1l);
		
		
		//when
		when(brandRepository.findById(1l)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(1l);
		//then
		
		assertEquals(1l, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());
		
	}
	
	
	@Test
	public void testGetByIdThrow() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1l);
		//when
		when(brandRepository.findById(2l)).thenReturn(Optional.empty());
		//brandService.getById(2);
		assertThatThrownBy(() -> brandService.getById(2l))
			.isInstanceOf(ResourceNotFoundException.class)
			/*.hasMessage("Brand With id = 2 not found")*/;
			//.hasMessage(String.format("%s With id = %d not found","Brand",2 ));
			//.hasMessageEndingWith("not found");
		//then
	}
	@Test
	void updateTest() {
		Brand brand=new Brand(1l,"Apple");
		Brand brand1=new Brand(1l,"Samsung");
		Long id = brand.getId();
		when(brandRepository.findById(id)).thenReturn(Optional.ofNullable(brand1));
		//when(brandRepository.save(any(Brand.class))).thenReturn(brand1);

		Brand update = brandService.update(id, brand1);
		verify(brandRepository,times(1)).findById(id);
		verify(brandRepository).save(brandCaptor.capture());
		assertEquals(1l,brandCaptor.getValue().getId());
		assertEquals("Samsung", brandCaptor.getValue().getName());
		
	}
	
	
}
