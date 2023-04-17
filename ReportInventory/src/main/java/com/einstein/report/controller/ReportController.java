package com.einstein.report.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.einstein.report.feign.ReportFeign;
import com.einstein.report.model.CartItem;
import com.einstein.report.model.InventoryReport;
import com.einstein.report.model.Product;
import com.einstein.report.model.Report;
import com.einstein.report.service.ReportService;

@RestController
@CrossOrigin(origins = "*")
public class ReportController {
	@Autowired
	ReportService reportservice;
	@Autowired
	ReportFeign reportfeign;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

	@GetMapping("/report")
	public ResponseEntity<?> getReport() {

		List<Product> product = reportfeign.getReport();

		LOGGER.info("Started to get the REPORT details");
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
	}

	@GetMapping("/cart/all")
	public List<CartItem> getallCartItems(@RequestParam int userId) {
		return reportfeign.getallCartItems(userId);
	}

	@GetMapping("/cartreport")
	public List<Report> generateCartReport(@RequestParam int userId) {
		return reportfeign.generateCartReport(userId);
	}

	@GetMapping("/productreport")
	public InventoryReport generateInventoryReport() {
		return reportservice.generateInventoryReport();
	}

}
