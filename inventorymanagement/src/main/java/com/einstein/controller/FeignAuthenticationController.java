package com.einstein.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.einstein.feign.BackendFeign;
import com.einstein.model.AuthenticateRequest;
import com.einstein.model.AuthenticationResponse;
import com.einstein.model.User;
import com.einstein.model.ValidateResponse;

@CrossOrigin("http://localhost:3000/")
@RestController
public class FeignAuthenticationController {
	@Autowired
	private BackendFeign backendFeign;

	@PostMapping("/validate")
	public ResponseEntity<AuthenticationResponse> validateJwt(@RequestHeader("Authorization") String jwtRequestHeader) {
		return new ResponseEntity<AuthenticationResponse>(backendFeign.validateJwt(jwtRequestHeader), HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<ValidateResponse> generateJwt(@RequestBody AuthenticateRequest request) {

		return new ResponseEntity<ValidateResponse>(backendFeign.generateJwt(request), HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {

		return new ResponseEntity<String>(backendFeign.registerUser(user), HttpStatus.OK);
	}
}
