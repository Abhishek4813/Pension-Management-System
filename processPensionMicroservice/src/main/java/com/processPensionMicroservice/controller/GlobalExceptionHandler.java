package com.processPensionMicroservice.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.processPensionMicroservice.exception.PensionerNotFoundException;
import com.processPensionMicroservice.model.CustomErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PensionerNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handlePensionerNotFoundException(PensionerNotFoundException ex){
		CustomErrorResponse customResponse=new CustomErrorResponse();
		customResponse.setTimestamp(LocalDateTime.now());
		customResponse.setMessage(ex.getMessage());
		customResponse.setReason("Invalid Details provided");
		customResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.OK);
	}
	
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex){
        CustomErrorResponse customResponse=new CustomErrorResponse();
        customResponse.setTimestamp(LocalDateTime.now());
        customResponse.setMessage(ex.getMessage());
        customResponse.setReason("Invalid Request Information");
        customResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.BAD_REQUEST);
    }
	
//	@ExceptionHandler(Exception.class)
//    public ResponseEntity<CustomErrorResponse> handleAuthorizationException(Exception ex){
//        CustomErrorResponse customResponse=new CustomErrorResponse();
//        customResponse.setTimestamp(LocalDateTime.now());
//        customResponse.setMessage(ex.getMessage());
//        customResponse.setReason("You must be authorized to access this information");
//        customResponse.setStatus(HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<CustomErrorResponse>(customResponse,HttpStatus.BAD_REQUEST);
//    }
}
