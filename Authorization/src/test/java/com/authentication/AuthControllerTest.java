package com.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.authentication.einstein.controller.AuthController;
import com.authentication.einstein.controller.FeignUserController;
import com.authentication.einstein.model.AuthenticationRequest;
import com.authentication.einstein.model.AuthenticationResponse;
import com.authentication.einstein.model.PersonalDetails;
import com.authentication.einstein.model.UserDetail;
import com.authentication.einstein.model.UserRegistration;
import com.authentication.einstein.model.ValidateResponse;
import com.authentication.einstein.service.ConstantFile;
import com.authentication.einstein.service.JwtService;
import com.authentication.einstein.service.UserDetailService;
import com.authentication.einstein.service.UserDetailsServiceImpl;
import com.authentication.einstein.service.ValidateResponseService;



@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	
    
	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Mock
	private FeignUserController feigncontroller;
	
	 @Mock
	    private ValidateResponse validateResponseMock;
	    
	    @InjectMocks
	    private ValidateResponseService validateResponseService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    @Mock
	    private HttpServletRequest request;

	    @Mock
	    private UserDetailService userDetailService;

	    @Mock
	    private JwtService jwtService;

	    @Mock
	    private ConstantFile constantFile;

	    @InjectMocks
	    private AuthController authController;

	@Test
	public void testSavePersonalDetails() {
		UserRegistration userRegistration = new UserRegistration();
		userRegistration.setEmailId("test@example.com");
		userRegistration.setUserName("testuser");
		userRegistration.setPassword("password");
		PersonalDetails expectedPersonalDetails = new PersonalDetails();
		expectedPersonalDetails.setEmailId("test@example.com");
		expectedPersonalDetails.setUserName("testuser");
		String encodedPassword = new BCryptPasswordEncoder().encode("password");
		expectedPersonalDetails.setPassword(encodedPassword);
		
		when(feigncontroller.saveUser(any())).thenReturn(ResponseEntity.ok(expectedPersonalDetails));
		
		userDetailsServiceImpl.savePersonalDetails(userRegistration);
		
	}

    @Test
    public void testValidateJwt() {
       
        String jwt = "Bearer test-jwt";
        when(request.getHeader("Authorization")).thenReturn(jwt);
        when(constantFile.getJwt(jwt)).thenReturn("test-jwt");
        String username = "test-username";
        UserDetail userDetail = new UserDetail();
        when(userDetailService.loadUserByUsername(username)).thenReturn(userDetail);
        when(jwtService.extractUsername("test-jwt")).thenReturn(username);
        when(jwtService.validateToken("test-jwt", userDetail)).thenReturn(true);
        ResponseEntity<AuthenticationResponse> response = authController.validateJwt(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AuthenticationResponse authenticationResponse = response.getBody();
        assertTrue(authenticationResponse.isValid());
    }

	
	
}





