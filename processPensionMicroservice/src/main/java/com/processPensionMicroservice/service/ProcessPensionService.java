package com.processPensionMicroservice.service;

import org.springframework.stereotype.Service;

import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;

@Service
public class ProcessPensionService {

	public PensionDetail getresult(PensionerDetail pensionerDetail) {
		double d = 0;
		PensionDetail pensionDetail;
		if (pensionerDetail.getPensionType().equalsIgnoreCase("self"))
			d = (pensionerDetail.getSalary() * 0.8 + pensionerDetail.getAllowance());
		else if (pensionerDetail.getPensionType().equalsIgnoreCase("family"))
			d = (pensionerDetail.getSalary() * 0.5 + pensionerDetail.getAllowance());
		pensionDetail = new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth(), pensionerDetail.getPan(), pensionerDetail.getPensionType(), d);
		return pensionDetail;

	}

	public ProcessPensionResponse checkdetails(PensionerInput pensionerInput, PensionerDetail pensionerDetail) {
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();

		if (pensionerInput.getName().equalsIgnoreCase(pensionerDetail.getName())
				&& (pensionerInput.getDateOfBirth().getDay() == pensionerDetail.getDateOfBirth().getDay()
						&& pensionerInput.getDateOfBirth().getMonth() == pensionerDetail.getDateOfBirth().getMonth()
						&& pensionerInput.getDateOfBirth().getYear() == pensionerDetail.getDateOfBirth().getYear())
				&& pensionerInput.getPan().equalsIgnoreCase(pensionerDetail.getPan())
				&& pensionerInput.getPensionType().equalsIgnoreCase(pensionerDetail.getPensionType())) {
			processPensionResponse.setPensionStatusCode(10);
		} else {
			processPensionResponse.setPensionStatusCode(21);
		}
		return processPensionResponse;
	}
}
