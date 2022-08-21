package com.processpensionmodule.processpension.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processpensionmodule.processpension.feign.AuthorisationClient;
import com.processpensionmodule.processpension.feign.PensionerDetailsClient;
import com.processpensionmodule.processpension.model.PensionDetail;
import com.processpensionmodule.processpension.model.PensionerInput;
import com.processpensionmodule.processpension.service.ProcessPensionServiceImpl;
import com.processpensionmodule.processpension.util.DateUtil;


/**
 * Test cases for Process pension Controller
 * 
 * @author Shubham Nawani
 *
 */

@WebMvcTest(ProcessPensionController.class)
class ProcessPensionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorisationClient authorisationClient;

	@MockBean
	private PensionerDetailsClient pensionerDetailsClient;

	

	@MockBean
	private ProcessPensionServiceImpl processPensionService;

	@Autowired
	private ObjectMapper objectMapper;
	

	private PensionerInput validPensionerInput;
	private PensionerInput invalidPensionerInput;
	

	// setup for process-pension input
	@BeforeEach
	void setup() throws ParseException {


		// valid PensionerInput
		validPensionerInput = new PensionerInput(
				"300546467895");

		// invalid PensionerInput
		invalidPensionerInput = new PensionerInput( "");

		// correct PensionDetails
		PensionDetail	pensionDetail = new PensionDetail("Shubham", DateUtil.parseDate("16-02-1999"), "ABCDE12345", "family", 50000);

	


	


	/***************************************
	 * 
	 * Test cases for GetPensionDetails()
	 * 
	 ***************************************
	 */
	@Test
	@DisplayName("Verify response after sending post request with valid data to /pensionerInput")
	void testGetPensionDetails_withValidInput() throws Exception {

		// performing test
		mockMvc.perform(post("/pensionerInput").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
				.content(objectMapper.writeValueAsString(validPensionerInput)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.pensionAmount", Matchers.equalTo(50000.0)));
	}

	@Test
	@DisplayName("Verify response after sending post request with invalid token to /pensionerInput")
	void testGetPensionDetails_withInvalidToken() throws Exception {
		
		// mock authorization microservice response for invalid token
		when(authorisationClient.validate(ArgumentMatchers.anyString())).thenReturn(false);

		// performing test
		mockMvc.perform(post("/pensionerInput").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(objectMapper.writeValueAsString(validPensionerInput)).accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "user1")).andExpect(status().isForbidden());
	}

	@Test
	@DisplayName("This method is responsible to test Global Handler")
	void testGlobalExceptions() throws Exception {

		final String errorMessage = "Invalid Credentials";

		// performing test
		mockMvc.perform(post("/pensionerInput").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
				.header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
				.content(objectMapper.writeValueAsString(invalidPensionerInput)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message", Matchers.equalTo(errorMessage)));
	}

	
	
	
	
}