package com.einstein.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.einstein.model.AuthenticateRequest;
import com.einstein.model.AuthenticationResponse;
import com.einstein.model.User;
import com.einstein.model.ValidateResponse;

//@FeignClient(name = "AUTHENTICATION-SERVICE", path = "/auth")
@FeignClient(name="backendFeign",url="localhost:8080/auth")
public interface BackendFeign {
	@PostMapping("/validate")
	public AuthenticationResponse validateJwt(@RequestHeader("Authorization") String jwtRequestHeader);

	@PostMapping("/authenticate")
	public ValidateResponse generateJwt(@RequestBody AuthenticateRequest request);

	@PostMapping("/register")
	public String registerUser(@RequestBody User user);
}
