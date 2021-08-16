package com.processPensionMicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service",url = "http://44.192.27.247:8081/auth")
public interface AuthorizationClient {
	@PostMapping(value = "/authorize")
	public boolean authorizeRequest(@RequestHeader(value = "Authorization",required = true) String header);
}
