import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.authentication.einstein.controller.AuthController;
import com.authentication.einstein.model.AuthenticationRequest;
import com.authentication.einstein.model.UserDetail;
import com.authentication.einstein.model.ValidateResponse;
import com.authentication.einstein.service.JwtService;
import com.authentication.einstein.service.UserDetailService;
import com.authentication.einstein.service.ValidateResponseService;

import io.jsonwebtoken.lang.Assert;

class AuthorizationTestCases {
	@Test
	public void testGenerateJwt() {
	    AuthenticationRequest request = new AuthenticationRequest("username", "password");
	    UserDetail userDetail = new UserDetail("username", "password", new ArrayList<>());
	    JwtService jwtServiceMock = Mockito.mock(JwtService.class);
	    ValidateResponseService validateResponseServiceMock = Mockito.mock(ValidateResponseService.class);
	    AuthController authController = new AuthController();
	    authController.authenticationManager = Mockito.mock(AuthenticationManager.class);
	    authController.userDetailService = Mockito.mock(UserDetailService.class);
	    authController.jwtService = jwtServiceMock;
	    authController.validateResponseService = validateResponseServiceMock;
	    
	    // Mocking authenticationManager.authenticate method to return void
	    Mockito.doNothing().when(authController.authenticationManager).authenticate(
	        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
	    
	    // Mocking userDetailService.loadUserByUsername method to return userDetail
	    Mockito.when(authController.userDetailService.loadUserByUsername(request.getUsername())).thenReturn(userDetail);
	    
	    // Mocking jwtService.generateToken method to return a dummy token
	    String dummyJwt = "dummyToken";
	    Mockito.when(jwtServiceMock.generateToken(userDetail)).thenReturn(dummyJwt);
	    
	    // Mocking validateResponseService.getValidateResponse method to return a dummy ValidateResponse
	    ValidateResponse dummyValidateResponse = new ValidateResponse(dummyJwt, userDetail);
	    Mockito.when(validateResponseServiceMock.getValidateResponse(dummyJwt, userDetail)).thenReturn(dummyValidateResponse);
	    
	    ResponseEntity<ValidateResponse> response = authController.generateJwt(request);
	    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	    Assert.assertEquals(dummyValidateResponse, response.getBody());
	}


	@Test
	void test() {
		fail("Not yet implemented");
	}

}
