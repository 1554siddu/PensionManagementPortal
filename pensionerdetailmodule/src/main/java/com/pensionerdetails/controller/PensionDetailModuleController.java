package com.pensionerdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pensionerdetails.dao.PensionDetailDaoImpl;
import com.pensionerdetails.exception.InvalidTokenException;
import com.pensionerdetails.feign.AuthorisationClient;
import com.pensionerdetails.models.PensionerDetail;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SREEKANTH GANTELA
 * 
 *         Pensioner Details Controller is to get the details of pensioner by
 *         passing the Aadhaar Number
 *
 */
@RestController
@Slf4j
@CrossOrigin
public class PensionDetailModuleController {

	@Autowired
	private PensionDetailDaoImpl pensionerDetailDao;

	
	@Autowired
	AuthorisationClient authorisationClient;

	/**
	 * @URL: http://localhost:8083/pensionerDetailByAadhaar/123456789012
	 * 
	 * @return if Aadhaar Number then return the pensioner details else throws
	 *         Exception
	 * 
	 * @Expceted: {
				  "name": "Achyuth",
				  "dateOfBirth": "1956-09-11T18:30:00.000+00:00",
				  "pan": "BHMER12436",
				  "salary": 27000,
				  "allowance": 10000,
				  "pensionType": "self",
				  "bank": {
				    "bankName": "ICICI",
				    "accountNumber": 12345678,
				    "bankType": "private"
				  }
				}
	 *
	 */

	@GetMapping("/pensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader(name = "Authorization") String token,@PathVariable String aadhaarNumber) {
		log.info("START - getPensionerDetailByAadhaar()");
		if (!authorisationClient.validate(token)) {
			throw new InvalidTokenException("You are not allowed to access this resource");
		}
		return pensionerDetailDao.getPensionerDetailByAadhaarNumber(aadhaarNumber);
	}

}

