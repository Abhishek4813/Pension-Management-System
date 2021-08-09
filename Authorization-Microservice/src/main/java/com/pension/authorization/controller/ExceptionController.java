package com.pension.authorization.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pension.authorization.exception.AuthenticationException;
import com.pension.authorization.model.ErrorModel;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler
	public ResponseEntity<ErrorModel>handleAuthenticationException(AuthenticationException e){
		ErrorModel error= new ErrorModel();
		error.setErrorId(HttpStatus.BAD_REQUEST.value());
		error.setErrorText(e.getMessage());
		error.setErrorLogTime(""+new Date());
		return ResponseEntity.status(HttpStatus.OK).body(error);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorModel>handleException(Exception e){
		ErrorModel error= new ErrorModel();
		error.setErrorId(HttpStatus.BAD_REQUEST.value());
		error.setErrorText(e.getMessage());
		error.setErrorLogTime(""+new Date());
		return ResponseEntity.status(HttpStatus.OK).body(error);
	}
}
