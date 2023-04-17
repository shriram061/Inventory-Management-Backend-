package com.einstein.exception;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is for customizing the exception handler
 */
@Getter
@Setter
@ApiModel(value = "Api Error Response Class")
public class ApiErrorResponse {

	private HttpStatus httpStatus;

	private String message;

	private String localizedMessage;

	ApiErrorResponse() {
	}

	public ApiErrorResponse(HttpStatus httpStatus) {
		this();
		this.httpStatus = httpStatus;
	}

	public ApiErrorResponse(HttpStatus httpStatus, Throwable throwable) {
		this();
		this.httpStatus = httpStatus;
		this.message = "Unexpected error in the request";
		this.localizedMessage = throwable.getLocalizedMessage();
	}

	public ApiErrorResponse(HttpStatus httpStatus, String message, Throwable throwable) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
		this.localizedMessage = throwable.getLocalizedMessage();
	}

}
