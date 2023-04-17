//package com.authentication;
//
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.authentication.einstein.model.AuthenticationRequest;
//import com.authentication.einstein.model.UserDetail;
//import com.authentication.einstein.model.ValidateResponse;
//import com.authentication.einstein.service.JwtService;
//import com.authentication.einstein.service.UserDetailService;
//import com.authentication.einstein.service.ValidateResponseService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//	@WebMvcTest
//	
//		@RunWith(SpringRunner.class)
//		
//	public class Test1 {
//
//		    @Autowired
//		    private MockMvc mockMvc;
//
//		    @MockBean
//		    private AuthenticationManager authenticationManager;
//
//		    @MockBean
//		    private JwtService jwtService;
//
//		    @MockBean
//		    private UserDetailService userDetailService;
//
//		    @MockBean
//		    private ValidateResponseService validateResponseService;
//
//		    @Test
//		    public void testGenerateJwt() throws Exception {
//		        AuthenticationRequest request = new AuthenticationRequest();
//		        request.setUsername("testuser");
//		        request.setPassword("testpassword");
//
//		        UserDetail userDetail = new UserDetail();
////		        userDetail.setUsername("");
////		        userDetail.setPassword("");
//
//		        when(userDetailService.loadUserByUsername("testuser")).thenReturn(userDetail);
//
//		        when(jwtService.generateToken(userDetail)).thenReturn("test-jwt");
//
//		        ValidateResponse expectedResponse = new ValidateResponse("test-jwt", "Login Successful");
//
//		        mockMvc.perform(post("/authenticate")
//		                .contentType(MediaType.APPLICATION_JSON)
//		                .content(new ObjectMapper().writeValueAsString(request)))
//		                .andExpect(status().isOk())
//		                .andExpect(jsonPath("$.jwt").value(expectedResponse.getJwt()))
//		                .andExpect(jsonPath("$.message").value(expectedResponse.getMessage()));
//		    }
//		
//
//	}
//
//	
//	
//	
//	
//	
//
