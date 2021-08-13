package com.processPensionMicroservice.controller;

import java.io.IOException;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.processPensionMicroservice.client.AuthorizationClient;
import com.processPensionMicroservice.client.PensionDisbursementClient;
import com.processPensionMicroservice.client.PensionerDetailClient;
import com.processPensionMicroservice.exception.AuthorizationException;
import com.processPensionMicroservice.exception.PensionerDetailsException;
import com.processPensionMicroservice.exception.PensionerNotFoundException;
import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessInput;
import com.processPensionMicroservice.model.ProcessPensionInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;
import com.processPensionMicroservice.service.ProcessPensionService;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/process")
public class processPensionController {

	@Autowired
	private PensionerDetailClient pensionerDetailClient;
	@Autowired
	private PensionDisbursementClient pensionDisbursementClient;
	@Autowired
	private ProcessPensionService processPensionService;
	@Autowired
	private AuthorizationClient authorizationClient;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * POST: localhost:8084/process/pensionDetail
	 * 
	 * { "name" : "Padmini", "dateOfBirth" : "2000-08-30", "pan" : "PCASD1234Q",
	 * "aadharNumber" : 102233445566, "pensionType" : "family" }
	 */

	@PostMapping("/PensionDetail")
	public PensionDetail getPensionDetails(@RequestHeader("Authorization") String header,
			@Valid @RequestBody PensionerInput pensionerInput)
			throws PensionerNotFoundException, PensionerDetailsException, AuthorizationException,RetryableException {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		log.info("start getPensionDetails");

		log.debug("" + pensionerInput);

		if (authorizationClient.authorizeRequest(header)) {
			PensionerDetail pensionerDetail = pensionerDetailClient.getPensionerDetailByAadhaar(header,
					pensionerInput.getAadharNumber());
			log.info(pensionerDetail.getName());
			if (pensionerDetail == null || pensionerDetail.getName() == null) {
				throw new PensionerNotFoundException("Pensioner with given aadhar not found");
			}
			PensionerDetail receivedPensionerDetail = modelMapper.map(pensionerInput, PensionerDetail.class);
			if (pensionerDetail.compareTo(receivedPensionerDetail) == -1) {
				throw new PensionerDetailsException("Incorrect Pensioner Details.");
			}

			double pensionAmount = processPensionService.getresult(pensionerDetail);
			log.info(""+pensionAmount);
			PensionDetail pensionDetail = modelMapper.map(pensionerDetail, PensionDetail.class);
			pensionDetail.setPensionAmount(pensionAmount);
			log.info(""+pensionDetail.getPensionAmount());
			return pensionDetail;

		} else {
			throw new AuthorizationException("User not authorized");
		}

	}

	/*
	 * POST: localhost:8084/process/ProcessPension
	 * 
	 * { "aadharNumber" : 112233445566, "pensionAmount": 23955.0, "serviceCharge":
	 * 500 }
	 */
	@PostMapping("/ProcessPension")
	public ProcessPensionResponse getcode(@RequestHeader("Authorization") String header,
			@Valid @RequestBody ProcessInput processInput)
			throws AuthorizationException, IOException, PensionerNotFoundException {
		log.info("start processPension");
		if (authorizationClient.authorizeRequest(header)) {
			PensionerDetail pensionerDetail = pensionerDetailClient.getPensionerDetailByAadhaar(header,
					processInput.getAadharNumber());
			double serviceCharge = processPensionService.getServiceCharge(pensionerDetail.getBank().getBankType());
			ProcessPensionInput processPensionInput = new ProcessPensionInput(processInput.getAadharNumber(),
					processInput.getPensionAmount(), serviceCharge);
			log.info("End ProcessPension");
			return pensionDisbursementClient.getcode(header, processPensionInput);
		} else {
			throw new AuthorizationException("User not authorized");
		}

	}

}
