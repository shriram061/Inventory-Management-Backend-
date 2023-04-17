package com.authentication.einstein.model;

import org.springframework.stereotype.Component;

import lombok.Data;

//fetching registered details
@Component
@Data
public class UserRegistration {

	private String emailId;

	private String userName;

	private String password;
	

}
