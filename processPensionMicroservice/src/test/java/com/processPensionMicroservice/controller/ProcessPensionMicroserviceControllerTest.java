package com.processPensionMicroservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.processPensionMicroservice.client.AuthorizationClient;
import com.processPensionMicroservice.client.PensionDisbursementClient;
import com.processPensionMicroservice.client.PensionerDetailClient;
import com.processPensionMicroservice.controller.processPensionController;
import com.processPensionMicroservice.exception.PensionerNotFoundException;
import com.processPensionMicroservice.model.Bank;
import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessPensionInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;
import com.processPensionMicroservice.repository.ProcessPensionRepository;
import com.processPensionMicroservice.service.ProcessPensionService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProcessPensionMicroserviceControllerTest {

	@InjectMocks
	processPensionController processController; 
	
	@Mock
	ProcessPensionService processPensionService;
	
	@Mock
	private ProcessPensionRepository processPensionRepository;
	
	@Mock
	AuthorizationClient authorizationClient;
	
	@MockBean
	PensionDisbursementClient pensionDisbursementClient;
	
	@MockBean
	PensionerDetailClient pensionerDetailClient;
	
	@Test
	public void getPensionDetailsTest() throws Exception
	{
		PensionerInput pensionerInput = new PensionerInput("Mounika", new Date(1998,11,27), "PCQAZ1285Q", 112233445566l, "family");
		Bank bank = new Bank("AndhraBank", 22334455l, "public");
		PensionerDetail pensionerDetail = new PensionerDetail("Mounika", new Date(1998,11,27), "PCQAZ1285Q", 70000, 12000, "family", bank);
		PensionDetail pensionDetail= new PensionDetail("Mounika", new Date(1998, 11, 27), "PCQAZ1285Q", "family", 46500.0);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse(10);
		ProcessPensionInput processPensionInput = new ProcessPensionInput(112233445566l,46500.0 , 500);
		
		Mockito.when(authorizationClient.authorizeRequest("")).thenReturn(true);
		
		Mockito.when(pensionerDetailClient.getPensionerDetailByAadhaar("",112233445566l)).thenReturn(pensionerDetail);
		
		Mockito.when(processPensionService.checkdetails(pensionerInput, pensionerDetail)).thenReturn(processPensionResponse);
		
		Mockito.when(processPensionService.getresult(pensionerDetail)).thenReturn(pensionDetail);
		
		Mockito.when(processController.getcode("",processPensionInput)).thenReturn(processPensionResponse);
		
		when(processPensionRepository.save(pensionDetail)).thenReturn(pensionDetail);
		
		assertEquals(processController.getPensionDetails("",pensionerInput), pensionDetail);
		
	}
	
	@Test
	public void getProcessCode21Test() throws IOException, PensionerNotFoundException
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput(112233445566l,23955.0 , 500);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse(21);
		Mockito.when(pensionDisbursementClient.getcode("",processPensionInput)).thenReturn(processPensionResponse);
		assertEquals(processController.getcode("",processPensionInput), processPensionResponse);
	}
	
	@Test
	public void getProcessCode10Test() throws IOException, PensionerNotFoundException
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput(102233445566l,23955.0 , 550);
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse(10);
		Mockito.when(pensionDisbursementClient.getcode("",processPensionInput)).thenReturn(processPensionResponse);
		assertEquals(processController.getcode("",processPensionInput), processPensionResponse);
	}
}
