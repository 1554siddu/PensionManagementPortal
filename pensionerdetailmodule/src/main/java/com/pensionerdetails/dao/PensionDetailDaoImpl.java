package com.pensionerdetails.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pensionerdetails.exception.NotFoundException;
import com.pensionerdetails.models.BankDetails;
import com.pensionerdetails.models.PensionerDetail;
import com.pensionerdetails.util.DateUtil;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j

public class PensionDetailDaoImpl implements PensionDetailDao {
	
	@Value("${errorMessage}")
	private String AADHAAR_NUMBER_NOT_FOUND;

	
	public PensionerDetail getPensionerDetailByAadhaarNumber(String aadhaarNumber) {

		String line = "";
		BufferedReader br = new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream("/details.csv")));
		try {
			while ((line = br.readLine()) != null) // returns a Boolean value
			{
				String[] person = line.split(",");
				// if Aadhaar number is found, then return the details
				if (aadhaarNumber.contentEquals(person[0])) {
					log.info("Details found");
					return new PensionerDetail(person[1], DateUtil.parseDate(person[2]), person[3],
							Double.parseDouble(person[4]), Double.parseDouble(person[5]), person[6],
							new BankDetails(person[7], Long.parseLong(person[8]), person[9]));
				}
			}
		} catch (NumberFormatException | IOException | ParseException e) {
			throw new NotFoundException(AADHAAR_NUMBER_NOT_FOUND);
		}
		throw new NotFoundException(AADHAAR_NUMBER_NOT_FOUND);
	}


}
