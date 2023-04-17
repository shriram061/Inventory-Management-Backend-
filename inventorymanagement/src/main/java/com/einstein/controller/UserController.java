package com.einstein.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.einstein.model.User;
import com.einstein.service.CartService;
import com.einstein.service.UserService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;

	@PostMapping("/saveuser")
	public ResponseEntity<User> viewPost(@RequestBody User user) {

		User user1 = userService.saveToUserByUser(user);
		cartService.createCart(user.getUserId());
		return new ResponseEntity<User>(user1, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
		User user1 = userService.updateUser(id, user);
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}

	@GetMapping("/getuser/{id}")
	public ResponseEntity<User> viewUser(@PathVariable int id) {
		User user1 = userService.getUserByUserId(id);
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}

	@GetMapping("/getusername")
	public ResponseEntity<User> getUserByUserName(@RequestParam("name") String username) {
		User user1 = userService.getUserByUserName(username);
		System.out.println(user1);
		System.out.println(username);
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}

	@GetMapping("/viewuser")
	public List<User> view() {
		List<User> user1 = userService.getAllUsers();
		return user1;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) {
		User user1 = userService.deleteUser(id);
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}
}
