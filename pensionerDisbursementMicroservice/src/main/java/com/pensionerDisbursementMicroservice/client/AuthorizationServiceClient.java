package com.pensionerDisbursementMicroservice.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name="auth-service",url = "http://localhost:8081/auth")
public interface AuthorizationServiceClient {
	
	@PostMapping(value = "/authorize")
	public boolean authorizeRequest(@RequestHeader(value = "Authorization",required = true) String header);
	
}
