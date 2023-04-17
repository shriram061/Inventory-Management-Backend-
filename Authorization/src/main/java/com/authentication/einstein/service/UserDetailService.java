package com.authentication.einstein.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authentication.einstein.controller.FeignUserController;
import com.authentication.einstein.model.PersonalDetails;
import com.authentication.einstein.model.UserDetail;

/**
 * 
 * This class is responsible for loading user details from the
 * FeignUserController.
 * 
 * It implements the UserDetailsService interface provided by Spring Security.
 */
@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private FeignUserController feigncontroller;

	/**
	 * 
	 * This method loads the user details based on the provided username.
	 * 
	 * @param username - the username of the user whose details are to be loaded
	 * 
	 * @return UserDetail - the user details for the given username
	 * 
	 * @throws UsernameNotFoundException - if the user details cannot be found for
	 *                                   the provided username
	 */
	@Override
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetail userDetail = null;
		PersonalDetails personalDetails = feigncontroller.getUserName(username);
		if (personalDetails != null) {
			userDetail = new UserDetail(feigncontroller.getUserName(username));
		}

		return userDetail;
	}

}
