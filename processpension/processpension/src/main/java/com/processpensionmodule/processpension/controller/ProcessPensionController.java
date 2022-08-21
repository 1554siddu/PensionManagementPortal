package com.processpensionmodule.processpension.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.processpensionmodule.processpension.exception.InvalidTokenException;
import com.processpensionmodule.processpension.feign.AuthorisationClient;
import com.processpensionmodule.processpension.feign.PensionerDetailsClient;
import com.processpensionmodule.processpension.model.PensionDetail;
import com.processpensionmodule.processpension.model.PensionerInput;
import com.processpensionmodule.processpension.service.ProcessPensionServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class ProcessPensionController {

	@Autowired
	ProcessPensionServiceImpl processPensionService;

	@Autowired
	AuthorisationClient authorisationClient;
	@Autowired 
	PensionerDetailsClient pensionerDetailsClient;

	/**
	 * @URL: http://localhost:8082/pensionerInput
	  { "aadhaarNumber": "123456789012"
	 */

	@PostMapping("/pensionerInput")
	public ResponseEntity<PensionDetail> getPensionDetails(@RequestHeader(name = "Authorization") String token,
			@RequestBody @Valid PensionerInput pensionerInput) {
		log.info("START - getPensionDetails()");
		if (!authorisationClient.validate(token)) {
			throw new InvalidTokenException("You are not allowed to access this resource");
		}
		log.info("END - getPensionDetails()");
		return new ResponseEntity<>(processPensionService.getPensionDetails(token,pensionerInput), HttpStatus.OK);
	}
}