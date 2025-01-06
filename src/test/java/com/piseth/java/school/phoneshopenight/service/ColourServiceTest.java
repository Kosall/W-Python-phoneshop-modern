package com.piseth.java.school.phoneshopenight.service;

/*@ExtendWith(MockitoExtension.class)

public class ColourServiceTest {
	@Mock
	private ColourRespository colourRespository;
	@Autowired
	private ColourService colourService;
	@BeforeEach
	public void setUp() {
		colourService= new ColourServiceImpl(colourRespository);
	}
	//@Test
	public void testBuild() {
		Colour colour=new Colour();
		colour.setName("Red");
		colourService.build(colour);
		verify(colourRespository,times(1)).save(colour);
		
	}
	//@Test
	public void testGetColourByIdHappyPart() {
		
		Colour colours=new Colour();
		colours.setId(1l);
		colours.setName("Red");
		
		when(colourRespository.findById(1l)).thenReturn(Optional.of(colours));
		
		
		Colour colourReturn = colourService.getById(1l);
		assertEquals(1l,  colourReturn.getId());
		assertEquals("Red", colourReturn.getName());
		
	}
	
	//@Test
	void testGetByIdThrowSadPart() {
		Colour colour=new Colour();
		colour.setId(2l);
		colour.setName("Brown");
		when(colourRespository.findById(1l)).thenReturn(Optional.empty());
		assertThatThrownBy(()->colourService.getById(1l))
		.isInstanceOf(ResourceNotFoundException.class)
		.hasMessage(String.format("%s With id = %d not found","Colour",1 ));
	}
	
	
	

}*/
