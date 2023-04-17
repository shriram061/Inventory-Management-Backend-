package com.einstein.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * String jwt - This is a single field of type String in the class.
 *  It represents a JSON Web Token (JWT) that will be validated by the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ValidateResponse {

	String jwt;

}
