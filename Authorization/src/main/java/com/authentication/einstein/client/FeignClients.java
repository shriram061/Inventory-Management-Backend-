package com.authentication.einstein.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.authentication.einstein.model.PersonalDetails;

@FeignClient(name = "INVENTORY-MANAGEMENT", path = "/user")
//@FeignClient(name="clientfiegn" ,url="localhost:9876/user")
public interface FeignClients {
	@GetMapping("/viewuser")
	public List<PersonalDetails> getAllUsers();

	@GetMapping("/getuser/{id}")
	public PersonalDetails getUser(@PathVariable int id);

	@GetMapping("/getusername")
	public PersonalDetails getUserName(@RequestParam("name") String username);

	@PostMapping("/saveuser")
	public PersonalDetails saveUser(@RequestBody PersonalDetails user);
}