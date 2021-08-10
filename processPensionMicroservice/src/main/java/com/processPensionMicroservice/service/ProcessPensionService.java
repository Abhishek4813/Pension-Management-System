package com.processPensionMicroservice.service;

import org.springframework.stereotype.Service;

import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessInput;
import com.processPensionMicroservice.model.ProcessPensionInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;

@Service
public class ProcessPensionService {

	public double getresult(PensionerDetail pensionerDetail) {
		double pensionAmount = 0;
//		PensionDetail pensionDetail;
		if (pensionerDetail.getPensionType().equalsIgnoreCase("self"))
			pensionAmount = (pensionerDetail.getSalary() * 0.8 + pensionerDetail.getAllowance());
		else if (pensionerDetail.getPensionType().equalsIgnoreCase("family"))
			pensionAmount = (pensionerDetail.getSalary() * 0.5 + pensionerDetail.getAllowance());
//		pensionDetail = new PensionDetail(pensionerDetail.getName(), pensionerDetail.getDateOfBirth(), pensionerDetail.getPan(), pensionerDetail.getPensionType(), d);
		return pensionAmount;

	}

//	public ProcessPensionResponse checkdetails(PensionerInput pensionerInput, PensionerDetail pensionerDetail) {
//		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
//
//		if (pensionerInput.getName().equalsIgnoreCase(pensionerDetail.getName())
//				&& (pensionerInput.getDateOfBirth().getDay() == pensionerDetail.getDateOfBirth().getDay()
//						&& pensionerInput.getDateOfBirth().getMonth() == pensionerDetail.getDateOfBirth().getMonth()
//						&& pensionerInput.getDateOfBirth().getYear() == pensionerDetail.getDateOfBirth().getYear())
//				&& pensionerInput.getPan().equalsIgnoreCase(pensionerDetail.getPan())
//				&& pensionerInput.getPensionType().equalsIgnoreCase(pensionerDetail.getPensionType())) {
//			processPensionResponse.setPensionStatusCode(10);
//		} else {
//			processPensionResponse.setPensionStatusCode(21);
//		}
//		return processPensionResponse;
//	}
	
	public double getServiceCharge(String bankType) {
//		ProcessPensionInput processPensionInput=new ProcessPensionInput();
		if(bankType.equalsIgnoreCase("public")) {
//			processPensionInput.setServiceCharge(500);
			return 500.0;
		}
		else {
//			processPensionInput.setServiceCharge(550);
			return 550.0;
		}
//		return processPensionInput;
	}
}
