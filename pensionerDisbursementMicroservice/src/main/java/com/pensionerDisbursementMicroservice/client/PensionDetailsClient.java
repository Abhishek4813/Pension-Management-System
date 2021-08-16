package com.pensionerDisbursementMicroservice.client;

import java.io.IOException;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pensionerDisbursementMicroservice.Model.PensionerDetail;

@FeignClient(name="pensioner-details-service", url = "http://3.235.225.18:8082/details")
public interface PensionDetailsClient {
	@PostMapping("/pensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader("Authorization") String header,@PathVariable long aadhaarNumber)
			throws IOException, PensionerDetailNotFoundException;
}