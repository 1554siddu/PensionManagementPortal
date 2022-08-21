package com.processpensionmodule.processpension.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.processpensionmodule.processpension.feign.PensionerDetailsClient;
import com.processpensionmodule.processpension.model.Bank;
import com.processpensionmodule.processpension.model.PensionDetail;
import com.processpensionmodule.processpension.model.PensionerDetail;
import com.processpensionmodule.processpension.util.DateUtil;


@SpringBootTest
class ProcessPensionServiceImplTest {

	@Autowired
	private IProcessPensionService processPensionService;

	@MockBean
	private PensionerDetailsClient pensionerDetailClient;

	

	

	@Test
	void testGettingPensionDetailByPassingPensionerDetalisForSelfPensionType() throws ParseException {
		Bank bank = new Bank("ICICI", 456678, "public");
		PensionerDetail details = new PensionerDetail("Shubham", DateUtil.parseDate("23-11-1996"), "ASDFG3456", 100000,
				10000, "self", bank);

		PensionDetail actualDetail = processPensionService.calculatePensionAmount(details);

		assertEquals(90000, actualDetail.getPensionAmount());
	}

	@Test
	void testGettingPensionDetailByPassingPensionerDetalisForFamilyPensionType() throws ParseException {
		Bank bank = new Bank("ICICI", 456678, "public");
		PensionerDetail details = new PensionerDetail("Shubham", DateUtil.parseDate("23-11-1996"), "ASDFG3456", 100000,
				10000, "family", bank);

		PensionDetail actualDetail = processPensionService.calculatePensionAmount(details);

		assertEquals(60000, actualDetail.getPensionAmount());
	}

}
