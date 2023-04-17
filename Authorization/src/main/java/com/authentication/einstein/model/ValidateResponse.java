package com.authentication.einstein.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//token
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ValidateResponse {

	String jwt;
	String message = "Login Successful";

}
