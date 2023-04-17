package com.authentication.einstein.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.einstein.model.UserDetail;
import com.authentication.einstein.model.ValidateResponse;

/**
 * 
 * This is a service class that handles the validation response of a JWT token
 * with a given UserDetail.
 * 
 * @param jwt        The JSON Web Token to be validated.
 * 
 * @param userDetail The user details used for validating the JWT token.
 * 
 * @return A ValidateResponse object that contains the validated JWT.
 */
@Service
public class ValidateResponseService {

	@Autowired
	private ValidateResponse validateResponse;

	public ValidateResponse getValidateResponse(String jwt, UserDetail userDetail) {
		validateResponse.setJwt(jwt);
		return validateResponse;
	}

}
