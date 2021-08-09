package com.pensionerDetailsMicroservice.Controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pensionerDetailsMicroservice.Exception.AadharNumberNotFoundException;
import com.pensionerDetailsMicroservice.Model.PensionerDetail;
import com.pensionerDetailsMicroservice.Service.PensionerdetailService;
import com.pensionerDetailsMicroservice.client.AuthorizationClient;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/details")
public class PensionerDetailsController {

	@Autowired
	private PensionerdetailService pensionerdetailService;
	
	@Autowired
	private AuthorizationClient authorizationClient;
	
	/*
	 * POST:   localhost:8082/pensionerDetailByAadhaar/102233445566
	 * 
	 */

	@PostMapping("/pensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader("Authorization") String header,@PathVariable long aadhaarNumber ) throws Exception  {
		
		log.info("start getPensionerDetailByAadhaar "+aadhaarNumber);

			log.debug(""+aadhaarNumber);
			log.info("end getPensionerDetailByAadhaar");
			if(authorizationClient.authorizeRequest(header)) {
				return pensionerdetailService.getPensionerDetailByAadhaarNumber(aadhaarNumber);
			}else {
				throw new Exception("User Not Authorized");
			}
			
	}
}
