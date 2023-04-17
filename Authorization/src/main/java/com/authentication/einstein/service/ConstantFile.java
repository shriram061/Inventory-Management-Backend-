package com.authentication.einstein.service;

import org.springframework.stereotype.Component;

/**
 * 
 * This method extracts the JWT token from the Authorization header value by
 * removing the "Bearer " prefix.
 * 
 * @param jwt the Authorization header value containing the JWT token
 * @return the JWT token without the "Bearer " prefix
 */
@Component
public class ConstantFile {

	public String getJwt(String jwt) {
		return jwt.substring(7);
	}
}
