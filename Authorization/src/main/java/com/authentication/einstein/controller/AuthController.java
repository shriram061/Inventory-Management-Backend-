package com.authentication.einstein.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.einstein.model.AuthenticationRequest;
import com.authentication.einstein.model.AuthenticationResponse;
import com.authentication.einstein.model.UserDetail;
import com.authentication.einstein.model.UserRegistration;
import com.authentication.einstein.model.ValidateResponse;
import com.authentication.einstein.service.ConstantFile;
import com.authentication.einstein.service.JwtService;
import com.authentication.einstein.service.UserDetailService;
import com.authentication.einstein.service.UserDetailsServiceImpl;
import com.authentication.einstein.service.ValidateResponseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ValidateResponse validateResponse;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private ValidateResponseService validateResponseService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private ConstantFile constantFile;

	Logger logger = LoggerFactory.getLogger("Auth-Controller-Logger");

	// authentication - for the very first login
	@PostMapping("/authenticate")
	@ApiOperation(value = "generateJwt", notes = "authenticate the registered details", httpMethod = "POST")
	public ResponseEntity<ValidateResponse> generateJwt(@Valid @RequestBody AuthenticationRequest request) {
		ResponseEntity<ValidateResponse> response = null;

		// authenticating the User-Credentials
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			// else when it authenticates successfully
			final UserDetail userDetail = userDetailService.loadUserByUsername(request.getUsername());

			final String jwt = jwtService.generateToken(userDetail); // returning the token as response

			// test
			logger.info("Authenticated User :: " + userDetail);

			validateResponse = validateResponseService.getValidateResponse(jwt, userDetail);

			response = new ResponseEntity<ValidateResponse>(validateResponse, HttpStatus.OK);

			logger.info("Successfully Authenticated user!");

		} catch (Exception e) {
			logger.error(e.getMessage() + "!! info about request-body : " + request);
			response = new ResponseEntity<ValidateResponse>(HttpStatus.FORBIDDEN);
		}
		logger.info("-------- Exiting /authenticate");
		return response;
	}

	// validate - for every request it validates the user-credentials from the
	// provided Jwt token in Authorization req. header
	@PostMapping("/validate")
	@ApiOperation(value = "ValidateJwt", notes = "validating the token that generated", httpMethod = "POST")
	public ResponseEntity<AuthenticationResponse> validateJwt(HttpServletRequest request) {
		String jwt = request.getHeader("Authorization");

		AuthenticationResponse authenticationResponse = new AuthenticationResponse("Invalid", false);
		ResponseEntity<AuthenticationResponse> response = null;
		logger.info("--------JWT :: " + jwt);
		// first remove Bearer from Header
		jwt = constantFile.getJwt(jwt); // dont use hardcoded data in controller

		// check token
		logger.info("--------JWT :: " + jwt);

		// check the jwt is proper or not
		final UserDetail userDetail = userDetailService.loadUserByUsername(jwtService.extractUsername(jwt));

		// now validating the jwt
		try {
			if (jwtService.validateToken(jwt, userDetail)) {
				authenticationResponse.setName(userDetail.getUsername());
				authenticationResponse.setValid(true);
				response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
				logger.info("Successfully validated the jwt and sending response back!");
			} else {
				response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
				logger.error("JWT Token validation failed!");
			}
		} catch (Exception e) {
//			logger.error(e.getMessage());
			response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
			logger.error("Exception occured whil validating JWT : Exception info : " + e.getMessage());
		}
		logger.info("-------- Exiting /validate");
		return response;
	}

	@PostMapping("/register")
	@ApiOperation(value = "registerUser", notes = "registering the user details", httpMethod = "POST")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistration userRegistration) {
		logger.info(userRegistration.getPassword());
		logger.info(userRegistration.getUserName());
		userDetailsServiceImpl.savePersonalDetails(userRegistration);
		return new ResponseEntity<String>("Registered Successfully", HttpStatus.OK);
	}

}
