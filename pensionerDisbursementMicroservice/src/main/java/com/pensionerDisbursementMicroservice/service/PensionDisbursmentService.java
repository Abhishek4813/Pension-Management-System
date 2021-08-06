package com.pensionerDisbursementMicroservice.service;

import org.springframework.stereotype.Service;

import com.pensionerDisbursementMicroservice.Model.Bank;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;

@Service
public class PensionDisbursmentService {

	public ProcessPensionResponse code(Bank bank, double serviceCharge) {
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		double x = bank.getBankType().equalsIgnoreCase("public") ? 500 : 550;
		if (x == serviceCharge)
			processPensionResponse.setPensionStatusCode(10);

		else {
			processPensionResponse.setPensionStatusCode(21);

		}

		return processPensionResponse;
	}

}
