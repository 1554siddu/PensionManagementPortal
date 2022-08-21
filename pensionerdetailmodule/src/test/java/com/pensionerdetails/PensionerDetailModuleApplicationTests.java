package com.pensionerdetails;

import java.io.IOException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerdetails.exception.NotFoundException;



@SpringBootTest
class PensionerDetailModuleApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	@Test
	void testMainMethod() throws NumberFormatException, IOException, NotFoundException, ParseException {
		PensionerDetailModuleApplication.main(new String [] {});
	}
}
