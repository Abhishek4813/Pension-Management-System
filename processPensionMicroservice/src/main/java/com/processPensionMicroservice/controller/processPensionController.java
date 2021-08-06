package com.processPensionMicroservice.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.processPensionMicroservice.client.AuthorizationClient;
import com.processPensionMicroservice.client.PensionDisbursementClient;
import com.processPensionMicroservice.client.PensionerDetailClient;
import com.processPensionMicroservice.exception.PensionerNotFoundException;
import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessPensionInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;
import com.processPensionMicroservice.repository.ProcessPensionRepository;
import com.processPensionMicroservice.service.ProcessPensionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class processPensionController {

	@Autowired
	PensionerDetailClient pensionerDetailClient;
	@Autowired
	PensionDisbursementClient pensionDisbursementClient;
	@Autowired
	ProcessPensionService processPensionService;
	@Autowired
	ProcessPensionRepository processPensionRepository;
	@Autowired
	AuthorizationClient authorizationClient;

	/*
	 * POST: localhost:8081/pensionerInput
	 * 
	 * { "name" : "Padmini", "dateOfBirth" : "2000-08-30", "pan" : "PCASD1234Q",
	 * "aadharNumber" : 102233445566, "pensionType" : "family" }
	 */

	@PostMapping("/pensionerInput")
	public PensionDetail getPensionDetails(@RequestHeader("Authorization") String header,
			@RequestBody PensionerInput pensionerInput) throws Exception {

		log.info("start getPensionDetails");

		log.debug("" + pensionerInput);
		System.out.println(pensionerInput.getAadharNumber());

		if (authorizationClient.authorizeRequest(header)) {
			PensionerDetail pensionerDetail = pensionerDetailClient
					.getPensionerDetailByAadhaar(pensionerInput.getAadharNumber());

			PensionDetail pensionDetail = null;

			if (pensionerDetail == null) {
				return pensionDetail;
			}

			ProcessPensionResponse processPensionResponse = processPensionService.checkdetails(pensionerInput,
					pensionerDetail);

			if (processPensionResponse.getPensionStatusCode() == 10) {
				pensionDetail = processPensionService.getresult(pensionerDetail);

				ProcessPensionInput processPensionInput = new ProcessPensionInput(pensionerInput.getAadharNumber(),
						pensionDetail.getPensionAmount(), 500);

				try {
					processPensionResponse = this.getcode(processPensionInput);
					if (processPensionResponse.getPensionStatusCode() == 21) {
						pensionDetail.setPensionAmount(pensionDetail.getPensionAmount() - 550);
					} else if (processPensionResponse.getPensionStatusCode() == 10) {
						pensionDetail.setPensionAmount(pensionDetail.getPensionAmount() - 500);
					}
					processPensionRepository.save(pensionDetail);
				} catch (IOException | PensionerNotFoundException e) {
					throw new PensionerNotFoundException("pensioner with given details is not found ");
				}
			}
			// Pension Details
			log.debug("" + pensionDetail);
			log.info("end getPensionDetails");

			return pensionDetail;
		} else {
			throw new Exception("User not authorized");
		}

	}

	/*
	 * POST: localhost:8081/ProcessPension
	 * 
	 * { "aadharNumber" : 112233445566, "pensionAmount": 23955.0, "serviceCharge":
	 * 500 }
	 */
	@PostMapping("/ProcessPension")
	public ProcessPensionResponse getcode(@RequestBody ProcessPensionInput processPensionInput)
			throws IOException, PensionerNotFoundException {
		log.info("start processPension");
		log.info("end processPension");
		return pensionDisbursementClient.getcode(processPensionInput);
	}

}
