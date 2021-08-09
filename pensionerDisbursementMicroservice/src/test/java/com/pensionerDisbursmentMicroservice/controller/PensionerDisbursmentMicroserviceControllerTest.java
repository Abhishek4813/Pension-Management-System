package com.pensionerDisbursmentMicroservice.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.Matchers.anything;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pensionerDisbursementMicroservice.Model.Bank;
import com.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pensionerDisbursementMicroservice.client.AuthorizationServiceClient;
import com.pensionerDisbursementMicroservice.client.PensionDetailsClient;
import com.pensionerDisbursementMicroservice.controller.PensionDisbursementController;
import com.pensionerDisbursementMicroservice.service.PensionDisbursmentService;


@SpringBootTest(classes = PensionerDisbursmentMicroserviceControllerTest.class)
public class PensionerDisbursmentMicroserviceControllerTest {

	@InjectMocks
	PensionDisbursementController controller;

	@Mock
	ProcessPensionResponse response;

	@Mock
	PensionDisbursmentService service;

	@Mock
	PensionDetailsClient client;
	
	@Mock
	AuthorizationServiceClient authorizationServiceClient;
	
	
	double serviceCharge = 500;
	
	@Test
	public void testControllerObjectNotNull() {
		assertNotNull(controller);
	}

	@Test
	public void testServiceObjectNotNull() {
		assertNotNull(service);
	}

	@Test
	public void testResponseObjectNotNull() {
		assertNotNull(response);
	}

	@Test
	public void testForPensionerHavingPublicBankAccount() throws Exception {
		Bank bank = new Bank("AndhraBank",22334455, "public");
		ProcessPensionInput processPensionInput = new ProcessPensionInput(112233445566L, 46500.00, serviceCharge);
		ProcessPensionResponse ppr = new ProcessPensionResponse();
		ppr.setPensionStatusCode(10);
		when(service.code(bank, serviceCharge)).thenReturn(ppr);
		when(client.getPensionerDetailByAadhaar("",112233445566L)).thenReturn(new PensionerDetail("Padmini", "30-08-2000", "PCASD1234Q", 45000, 2000, "family", bank));
		when(authorizationServiceClient.authorizeRequest("")).thenReturn(true);
		response = controller.getcode("",processPensionInput);
		assertEquals(10, response.getPensionStatusCode());
		assertTrue(true);
	}
}
