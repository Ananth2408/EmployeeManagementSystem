package com.ideas2it.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ideas2it.employee.constant.Constant;

@RestControllerAdvice
public class EMSExceptionHandlers {

	@ExceptionHandler(value = { EMSException.class })
	public ResponseEntity<Object> myMessage(EMSException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> myMessage(MethodArgumentTypeMismatchException e) {

		return new ResponseEntity<>(Constant.DETALILS_NOTEXIST, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity<Object> myMessage(HttpMessageNotReadableException e) {

		return new ResponseEntity<>(Constant.INSERTION_EXCEPTION, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = { IllegalStateException.class })
	public ResponseEntity<Object> myMessage(IllegalStateException e) {

		return new ResponseEntity<>(Constant.DETALILS_NOTEXIST, HttpStatus.FORBIDDEN);
	}
}
