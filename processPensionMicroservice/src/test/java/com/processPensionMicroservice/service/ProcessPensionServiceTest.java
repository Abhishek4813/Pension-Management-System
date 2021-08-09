package com.processPensionMicroservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.processPensionMicroservice.model.Bank;
import com.processPensionMicroservice.model.PensionDetail;
import com.processPensionMicroservice.model.PensionerDetail;
import com.processPensionMicroservice.model.PensionerInput;
import com.processPensionMicroservice.model.ProcessPensionResponse;
import com.processPensionMicroservice.util.DateUtil;

@SpringBootTest(classes = ProcessPensionServiceTest.class)
public class ProcessPensionServiceTest {

	@InjectMocks
	ProcessPensionService processPensionService;



	@Test
	public void testCheckDetailsForCorrectPensionerInputForSelfPensionType() throws ParseException {
		PensionerInput input = new PensionerInput("Jahnavi", DateUtil.parseDate("21-09-1999"), "JAHNA1001I",
				122233445566L, "self");
		Bank bank = new Bank("HDFC", 33445566, "private");
		PensionerDetail details = new PensionerDetail("Jahnavi", DateUtil.parseDate("21-09-1999"), "JAHNA1001", 30001,
				12001, "self", bank);

		ProcessPensionResponse response = processPensionService.checkdetails(input, details);

		assertEquals(21, response.getPensionStatusCode());
	}

	@Test
	public void testCheckDetailsForCorrectPensionerInputForFamilyPensionType() throws ParseException {
		PensionerInput input = new PensionerInput("Padmini", DateUtil.parseDate("30-08-2000"), "PCASD1234Q",
				102233445566L, "family");
		Bank bank = new Bank("SBI", 11223344, "private");
		PensionerDetail details = new PensionerDetail("Padmini", DateUtil.parseDate("30-08-2000"), "PCASD1234Q", 45000,
				2000, "family", bank);

		ProcessPensionResponse response = processPensionService.checkdetails(input, details);

		assertEquals(10, response.getPensionStatusCode());
	}

	@Test
	public void testCheckDetailsForIncorrectPensionerInputForSelf() throws ParseException {
		PensionerInput input = new PensionerInput("Jahnavi", DateUtil.parseDate("23-11-1996"), "ASDFG3908",
				123456789012L, "self");
		Bank bank = new Bank("ICICI", 456678, "public");
		PensionerDetail details = new PensionerDetail("Jahnavi", DateUtil.parseDate("23-11-1996"), "ASDFG3456", 100000,
				10000, "self", bank);

		ProcessPensionResponse response = processPensionService.checkdetails(input, details);

		assertEquals(21, response.getPensionStatusCode());
	}

	@Test
	public void testCheckDetailsForIncorrectPensionerInputForFamily() throws ParseException {
		PensionerInput input = new PensionerInput("mounika", DateUtil.parseDate("23-11-1996"), "ASDFG3908",
				123456789012L, "family");
		Bank bank = new Bank("ICICI", 456678, "public");
		PensionerDetail details = new PensionerDetail("mounika", DateUtil.parseDate("23-11-1996"), "ASDFG3456", 100000,
				10000, "family", bank);

		ProcessPensionResponse response = processPensionService.checkdetails(input, details);

		assertEquals(21, response.getPensionStatusCode());
	}

	@Test
	public void testGettingPensionDetailByPassingPensionerDetalisForSelfPensionType() throws ParseException {
		Bank bank = new Bank("HDFC", 33445566, "private");
		PensionerDetail details = new PensionerDetail("Jahnavi", DateUtil.parseDate("21-09-1999"), "JAHNA1001I", 30001,
				12001, "self", bank);

		PensionDetail actualDetail = processPensionService.getresult(details);

		assertEquals(36001.8, actualDetail.getPensionAmount());
	}

	@Test
	public void testGettingPensionDetailByPassingPensionerDetalisForFamilyPensionType() throws ParseException {
		Bank bank = new Bank("SBI", 11223344, "private");
		PensionerDetail details = new PensionerDetail("Padmini", DateUtil.parseDate("30-08-1990"), "PCASD1234Q", 45000,
				2000, "family", bank);

		PensionDetail actualDetail = processPensionService.getresult(details);

		assertEquals(24500.0, actualDetail.getPensionAmount());
	}

}
