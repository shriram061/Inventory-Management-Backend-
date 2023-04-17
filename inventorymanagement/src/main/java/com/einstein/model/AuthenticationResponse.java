package com.einstein.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AuthenticationResponse {
	String name;
	boolean valid;

	@Override
	public String toString() {
		return "AuthenticationResponse [name=" + name + ", valid=" + valid + "]";
	}

}