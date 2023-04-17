package com.einstein.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(ProductNotFoundException exception) {
		ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND);
		errorResponse.setLocalizedMessage(exception.getLocalizedMessage());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
