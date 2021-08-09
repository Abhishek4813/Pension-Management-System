package com.pension.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.log4j.Log4j2;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Log4j2
@EnableSwagger2
@EnableDiscoveryClient
public class AuthorizationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
		log.info("Inside Main Method..");
	}

}
