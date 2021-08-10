package com.pensionerDisbursmentMicroservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerDisbursementMicroservice.Model.Bank;
import com.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pensionerDisbursementMicroservice.service.PensionDisbursmentService;

@SpringBootTest(classes = PensionDisbursementServiceTest.class)
public class PensionDisbursementServiceTest {

	@InjectMocks
	PensionDisbursmentService service;
	
	@Mock
	ProcessPensionResponse response;
	
	int serviceCharge = 500;
	
	@Test
	public void testServiceObjectNotNull() {
		assertNotNull(service);
	}
	
	@Test
	public void testResponseObjectNotNull() {
		assertNotNull(response);
	}
	
	@Test
	public void testGettingCodeForPublicTypeBank() {
		Bank bank = new Bank("AndhraBank", 22334455, "public");
//		response = service.code(bank, serviceCharge);
		assertEquals(10, response.getPensionStatusCode());
	}
	
	@Test
	public void testGettingCodeForPrivateTypeBank() {
		Bank bank = new Bank("SBI", 11223344, "private");
//		response = service.code(bank, serviceCharge);
		assertEquals(21, response.getPensionStatusCode());
	}

}
