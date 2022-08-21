package com.processpensionmodule.processpension.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.processpensionmodule.processpension.model.PensionerInput;

import lombok.extern.slf4j.Slf4j;

/**
 * Test class to test all the repository functionality
 */
@SpringBootTest
@Slf4j
class PensionDetailsRepositoryTests {

	@Autowired
	private PensionerDetailsRepository pensionerDetailsRepository;

	@Test
	@DisplayName("This method is responsible to test save() for pensioner details")
	void testSaveForPensionerDetails() throws ParseException {
		log.info("START - testSaveForPensionerDetails()");

		PensionerInput pi_empty = new PensionerInput();
		PensionerInput pi = new PensionerInput("211228329912");

		PensionerInput savedDetails = pensionerDetailsRepository.save(pi);
		assertEquals(savedDetails.getAadhaarNumber(), pi.getAadhaarNumber());
		assertNotNull(savedDetails);

		log.info("END - testSaveForPensionerDetails()");
	}
}