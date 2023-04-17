package com.authentication.einstein.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authentication.einstein.controller.FeignUserController;
import com.authentication.einstein.model.PersonalDetails;
import com.authentication.einstein.model.UserRegistration;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger("Auth-Controller-Logger");

	@Autowired
	private FeignUserController feigncontroller;

	/**
	 * Saves the personal details of a user during registration.
	 * 
	 * @param userRegistration the UserRegistration object containing the user's
	 *                         registration details.
	 * @return void
	 */
	@Override
	public void savePersonalDetails(UserRegistration userRegistration) {
		PersonalDetails personalDetails = new PersonalDetails();

		personalDetails.setEmailId(userRegistration.getEmailId());
		
		personalDetails.setUserName(userRegistration.getUserName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(userRegistration.getPassword());
		personalDetails.setPassword(encodedPassword);

		feigncontroller.saveUser(personalDetails);
		logger.info(personalDetails.getPassword());
		logger.info("User Data Inserted SuccessFUlly");
	}

	/**
	 * Retrieves the personal details of a user based on the JWT token.
	 * 
	 * @param jwt the JWT token used to authenticate the user.
	 * @return the PersonalDetails object containing the user's personal details.
	 */
	@Override
	public PersonalDetails getPersonalDetials(String jwt) {
		return null;
	}

}
