package com.processpensionmodule.processpension.service;

import com.processpensionmodule.processpension.model.PensionDetail;
import com.processpensionmodule.processpension.model.PensionerDetail;
import com.processpensionmodule.processpension.model.PensionerInput;

public interface IProcessPensionService {

	public PensionDetail getPensionDetails(String token,PensionerInput pensionerInput);
	public PensionDetail calculatePensionAmount(PensionerDetail pensionDetail);
	
	public double getServiceCharge(PensionerDetail pensionerDetail);
	
}