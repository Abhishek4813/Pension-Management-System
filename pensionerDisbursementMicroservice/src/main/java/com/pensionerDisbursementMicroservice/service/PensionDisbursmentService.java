package com.pensionerDisbursementMicroservice.service;

import org.springframework.stereotype.Service;

import com.pensionerDisbursementMicroservice.Model.Bank;
import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;

@Service
public class PensionDisbursmentService {

//	public ProcessPensionResponse code(Bank bank, double serviceCharge) {
//		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
//		double x = bank.getBankType().equalsIgnoreCase("public") ? 500 : 550;
//		if (x == serviceCharge)
//			processPensionResponse.setPensionStatusCode(10);
//
//		else {
//			processPensionResponse.setPensionStatusCode(21);
//
//		}
//
//		return processPensionResponse;
//	}
	
	public ProcessPensionResponse code(PensionerDetail pensionerDetail, ProcessPensionInput processPensionInput) {
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		final double publicBankCharge=500.0;
		final double privateBankCharge=550.0;
		double serviceCharge = pensionerDetail.getBank().getBankType().equalsIgnoreCase("public") ? publicBankCharge : privateBankCharge;
		
		double pensionAmount=pensionerDetail.getPensionType().equalsIgnoreCase("self")?((0.8*pensionerDetail.getSalary())+pensionerDetail.getAllowance()):((0.5*pensionerDetail.getSalary())+pensionerDetail.getAllowance());
//		System.out.println(pensionerDetail.getPensionType()+" "+pensionAmount+" "+pensionerDetail.getAllowances());
//		System.out.println(serviceCharge+" "+processPensionInput.getServiceCharge()+" "+pensionAmount+""+processPensionInput.getPensionAmount());
		if (serviceCharge == processPensionInput.getServiceCharge() && pensionAmount==processPensionInput.getPensionAmount())
			processPensionResponse.setPensionStatusCode(10);

		else {
			processPensionResponse.setPensionStatusCode(21);

		}

		return processPensionResponse;
	}

}
