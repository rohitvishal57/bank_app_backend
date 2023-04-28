package com.monocept.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<EmployeeExceptionResponse> handleException(AccountNotFoundException e){
		EmployeeExceptionResponse response = new EmployeeExceptionResponse();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(e.getMessage());
		response.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeExceptionResponse> handleException(Exception e){
		EmployeeExceptionResponse response = new EmployeeExceptionResponse();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setMessage("Given URL not found......");
		response.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}

}
