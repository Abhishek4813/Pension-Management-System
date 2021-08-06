package com.pensionerDisbursementMicroservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pensionerDisbursementMicroservice.client.PensionDetailsClient;
import com.pensionerDisbursementMicroservice.service.PensionDisbursmentService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PensionDisbursementController {

	@Autowired
	private PensionDetailsClient pensionDetailsClient;

	@Autowired
	private PensionDisbursmentService pensionDisbursmentService;
	
	/*
	 * POST:  localhost:8083/disbursePension
	 * 
	 * {
	 *      "aadharNumber" : 102233445566,
	 *      "pensionAmount": 23950.0,
	 *      "serviceCharge": 500
	 * }
	 * Output: 10 (success code)
	 */
	
	
	/*
	 * POST:  localhost:8083/disbursePension
	 * 
	 * {
	 *      "aadharNumber" : 102233445566,
	 *      "pensionAmount": 23950.0,
	 *      "serviceCharge": 550
	 * }
	 * Output: 21 (failure code)
	 */

	@PostMapping("/disbursePension")
	public ProcessPensionResponse getcode(@RequestBody ProcessPensionInput processPensionInput)
			throws IOException, PensionerDetailNotFoundException {
	
		log.info("Start getcode");
		
		
		try
		{
			ProcessPensionResponse processPensionResponse = pensionDisbursmentService.code(
				pensionDetailsClient.getPensionerDetailByAadhaar(processPensionInput.getAadharNumber()).getBank(),
				processPensionInput.getServiceCharge());
		
		log.debug("The code is "+processPensionResponse);
		
		log.info("End getcode");
		
		return processPensionResponse;
	 }
		catch(PensionerDetailNotFoundException e)
		{
	     throw new PensionerDetailNotFoundException("pensioneer with given aadhaar number " +processPensionInput.getAadharNumber()+ " is not found ");
		}

}
}
