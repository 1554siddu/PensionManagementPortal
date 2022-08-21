package com.processpensionmodule.processpension.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.processpensionmodule.processpension.model.PensionerDetail;



/**
 * Feign client to connect with Pension details micro-service
 */
@FeignClient(name="PENSIONER-DETAIL-SERVICE",url="localhost:8083")
public interface PensionerDetailsClient {
	@GetMapping("/pensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader(name = "Authorization") String token,@PathVariable String aadhaarNumber);
}