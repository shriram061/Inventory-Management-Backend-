package com.einstein.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AuthenticateRequest {

	private String username;
	private String password;
}
