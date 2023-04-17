package com.authentication.einstein.service;

import com.authentication.einstein.model.PersonalDetails;
import com.authentication.einstein.model.UserRegistration;

public interface UserDetailsService {
	/**
	 * Saves the personal details of a user during registration.
	 *
	 * @param userRegistration the UserRegistration object containing the user's
	 *                         personal details
	 */
	public void savePersonalDetails(UserRegistration userRegistration);

	/**
	 * Retrieves the personal details of a user using their JWT token.
	 *
	 * @param jwt the JWT token of the user
	 * @return the PersonalDetails object containing the user's personal details
	 */
	public PersonalDetails getPersonalDetials(String jwt);
}
