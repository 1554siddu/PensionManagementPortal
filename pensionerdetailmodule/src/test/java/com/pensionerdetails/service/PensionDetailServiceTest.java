package com.pensionerdetails.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.text.ParseException;

//import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerdetails.dao.PensionDetailDao;
import com.pensionerdetails.exception.NotFoundException;
import com.pensionerdetails.models.BankDetails;
import com.pensionerdetails.models.PensionerDetail;
import com.pensionerdetails.util.DateUtil;
/**
 * @author SREEKANTH GANTELA
 * 
 *         This is the service test class
 * 
 *         Test cases for the Pension Detail Service
 *
 */
@SpringBootTest
class PensionDetailServiceTest {

	@Autowired
	PensionDetailDao pdd;

	@Value("${errorMessage}")
	private String AADHAAR_NUMBER_NOT_FOUND;

	/**
	 * Test the Pensioner Detail service is null or not
	 */

	@Test
	void testNotNullPensionDetailServiceObject() {
		assertNotNull(pdd);
	}

	/**
	 * Test Case for the Correct Details Returned From Service With Correct
	 * AadharNumber
	 */

	@Test
	void testCorrectDetailsReturnedFromServiceWithCorrectAadharNumber()
			throws IOException, NotFoundException, NumberFormatException,
			com.pensionerdetails.exception.NotFoundException, ParseException, NullPointerException {
		final String aadhaarNumber = "123456789012";

		PensionerDetail pensionerDetail = new PensionerDetail("Achyuth", DateUtil.parseDate("12-09-1956"),
				"BHMER12436", 27000, 10000, "self", new BankDetails("ICICI", 12345678, "private"));
		assertEquals(pdd.getPensionerDetailByAadhaarNumber(aadhaarNumber).getPan(), pensionerDetail.getPan());
		assertEquals(pdd.getPensionerDetailByAadhaarNumber(aadhaarNumber).getBank().getAccountNumber(), pensionerDetail.getBank().getAccountNumber());
	}

	/**
	 * Test Case for the incorrect details returned From Service With Invalid
	 * Aadhaar Number
	 */

	@Test
	void testForIncorrectAadharNumber() {
		final String aadhaarNumber = "12345678";

		NotFoundException exception = assertThrows(NotFoundException.class,
				() -> pdd.getPensionerDetailByAadhaarNumber(aadhaarNumber));
		assertEquals(exception.getMessage(), AADHAAR_NUMBER_NOT_FOUND);
		assertNotNull(exception);
	}
}