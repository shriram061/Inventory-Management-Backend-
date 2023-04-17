package com.authentication.einstein.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.einstein.client.FeignClients;
import com.authentication.einstein.model.PersonalDetails;

import io.swagger.annotations.ApiOperation;

@RestController
public class FeignUserController {
	@Autowired
	private FeignClients feignclient;

	@GetMapping("/viewuser")
	@ApiOperation(value = "getAllUsers", notes = "get the details of the Users registered", httpMethod = "GET")
	public ResponseEntity<List<PersonalDetails>> getAllUsers() {
		List<PersonalDetails> userDetail = feignclient.getAllUsers();

		return new ResponseEntity<List<PersonalDetails>>(userDetail, HttpStatus.OK);
	}

	@GetMapping("/getuser/{id}")
	@ApiOperation(value = "getUser", notes = "get the details of User by Id", httpMethod = "GET")
	public ResponseEntity<PersonalDetails> getUser(@PathVariable int id) {
		PersonalDetails userdetail = feignclient.getUser(id);
		return new ResponseEntity<PersonalDetails>(userdetail, HttpStatus.OK);
	}

	@GetMapping("/getusername")
	@ApiOperation(value = "getUserName", notes = "getting the UserName details", httpMethod = "GET")
	public PersonalDetails getUserName(@RequestParam("name") String username) {
		PersonalDetails userdetail = feignclient.getUserName(username);
		return userdetail;
	}

	@PostMapping("/saveuser")
	@ApiOperation(value = "saveUser", notes = "saving the users details", httpMethod = "POST")
	public ResponseEntity<PersonalDetails> saveUser(@RequestBody PersonalDetails user) {
		System.out.println("adfafd--");
		PersonalDetails userdetail = feignclient.saveUser(user);

		return new ResponseEntity<PersonalDetails>(userdetail, HttpStatus.OK);
	}
}
