package com.processPensionMicroservice.service;

import org.springframework.stereotype.Service;

import com.processPensionMicroservice.model.PensionerDetail;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProcessPensionServiceImpl implements ProcessPensionService{

	public double getresult(PensionerDetail pensionerDetail) {
		double pensionAmount = 0;
		if (pensionerDetail.getPensionType().equalsIgnoreCase("self"))
			pensionAmount = (pensionerDetail.getSalary() * 0.8 + pensionerDetail.getAllowance());
		else if (pensionerDetail.getPensionType().equalsIgnoreCase("family"))
			pensionAmount = (pensionerDetail.getSalary() * 0.5 + pensionerDetail.getAllowance());
		log.info(pensionAmount);
		return pensionAmount;

	}


	
	public double getServiceCharge(String bankType) {
		if(bankType.equalsIgnoreCase("public")) {
			return 500.0;
		}
		else {
			return 550.0;
		}
	}
}
