package com.einstein.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.einstein.model.ContactUs;
import com.einstein.service.ContactUsService;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*")
public class ContactUsController {

	@Autowired
	ContactUsService contactService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsController.class);

	@PostMapping("/querypost")
	public ResponseEntity<?> saveQuery(@RequestBody ContactUs contact) {
		LOGGER.info("A new query has been generated");
		ContactUs item = contactService.saveQuery(contact);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@GetMapping("/viewquery")
	public ResponseEntity<?> getQuery() {
		LOGGER.info("Started to get the product details");
		List<ContactUs> query = contactService.getAllQueries();
		return new ResponseEntity<>(query, HttpStatus.OK);
	}

	@GetMapping("/viewquery/{id}")

	public ResponseEntity<ContactUs> viewQueryById(@PathVariable("id") int id) {
		LOGGER.info("Started to get the product details by id");

		ContactUs query = contactService.viewQueryById(id);

		return new ResponseEntity<>(query, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteQuery(@PathVariable("id") int id) {
		LOGGER.info("Started to delete the query");
		contactService.deleteProduct(id);
		return new ResponseEntity<>("Query deleted", HttpStatus.OK);
	}

}
