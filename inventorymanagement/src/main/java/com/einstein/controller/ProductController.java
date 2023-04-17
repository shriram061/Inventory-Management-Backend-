package com.einstein.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.einstein.model.Product;

import com.einstein.model.User;
import com.einstein.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class ProductController {

	@Autowired
	ProductService productservice;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@PostMapping("/add")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		LOGGER.info("Started to add the details");
		Product item = productservice.saveProduct(product);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<?> getProduct() {
		LOGGER.info("Started to get the product details");
		List<Product> product = productservice.getAllProduct();
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/view/{id}")

	public ResponseEntity<Product> viewProductById(@PathVariable("id") int id) {
		LOGGER.info("Started to get the product details by id");

		Product product = productservice.viewProductById(id);

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/product/view/{id}")
	public Optional<Product> view(@PathVariable("id") int id) {
		return productservice.view(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		LOGGER.info("Started to delete the details");
		productservice.deleteProduct(id);
		return new ResponseEntity<>("Product deleted", HttpStatus.OK);
	}

	@PutMapping("/view/update/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestBody Product products) {
		LOGGER.info("Started to update the details");
		Product product = productservice.updateProduct(products, id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestParam String keyword) {
		List<Product> products = productservice.listAll(keyword);
		LOGGER.info("Started to search the details");

		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("/searchid")
	public ResponseEntity<?> searchId(@RequestParam int id) {
		List<Product> products = productservice.listAllId(id);
		LOGGER.info("Started to search the details Id");

		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/report")
	public List<Product> getReport() {
		LOGGER.info("Started to get the product report");
		List<Product> product = productservice.getAllProduct();
		return product;
	}

}