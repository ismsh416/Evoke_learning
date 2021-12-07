package com.Covid.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
public ResponseEntity<?> idNotFoundException(IdNotFoundException idex, WebRequest request)
{
	ErrorDetails errorDetails = new ErrorDetails(new Date(), idex.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	
}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception idex, WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(new Date(),idex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
