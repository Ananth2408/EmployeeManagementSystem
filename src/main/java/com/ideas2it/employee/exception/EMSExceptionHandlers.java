package com.ideas2it.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EMSExceptionHandlers {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> myMessage(Exception e){
		
		return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value = {EMSException.class})
	public ResponseEntity<Object> myMessage(EMSException e){
		
		return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
		
	}

}
