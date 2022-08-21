package com.processpensionmodule.processpension.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.processpensionmodule.processpension.feign.PensionerDetailsClient;
import com.processpensionmodule.processpension.model.PensionDetail;
import com.processpensionmodule.processpension.model.PensionerDetail;
import com.processpensionmodule.processpension.model.PensionerInput;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessPensionServiceImpl implements IProcessPensionService {
	

	@Autowired
	private PensionerDetailsClient pensionerDetailClient;
	
	

	@Override
	public PensionDetail getPensionDetails(String token,PensionerInput pensionerInput) {

		 PensionerDetail pensionerDetail = pensionerDetailClient
				.getPensionerDetailByAadhaar(token,pensionerInput.getAadhaarNumber());
		 
		return calculatePensionAmount(pensionerDetail);

	}

	@Override
	public PensionDetail calculatePensionAmount(PensionerDetail pensionDetail) {
		double pensionAmount = 0;
		if (pensionDetail.getPensionType().equalsIgnoreCase("self"))
			pensionAmount = (pensionDetail.getSalary() * 0.8 + pensionDetail.getAllowance());
		else if (pensionDetail.getPensionType().equalsIgnoreCase("family"))
			pensionAmount = (pensionDetail.getSalary() * 0.5 + pensionDetail.getAllowance());

		return new PensionDetail(pensionDetail.getName(), pensionDetail.getDateOfBirth(), pensionDetail.getPan(),
				pensionDetail.getPensionType(), pensionAmount, getServiceCharge(pensionDetail));
	}

	public double getServiceCharge(PensionerDetail pensionerDetail) {
		double bankServiceCharge = 0.0;
		if (pensionerDetail.getBank().getBankType().equalsIgnoreCase("public")) {
			bankServiceCharge = 500.0;
		} else {
			bankServiceCharge = 550.0;
		}
		return bankServiceCharge;

	}
}
