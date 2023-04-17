package com.einstein.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Class for handling EntityException
 *
 */
@ControllerAdvice
public class EntityExceptionHandler {

	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}