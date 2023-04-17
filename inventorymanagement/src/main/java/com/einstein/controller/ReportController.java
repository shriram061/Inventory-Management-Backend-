package com.einstein.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.einstein.model.InventoryReport;
import com.einstein.model.Report;
import com.einstein.service.ReportService;

@RestController
@CrossOrigin(origins = "*")
public class ReportController {
	@Autowired
	private ReportService reportService;

	@GetMapping("/cartreport")
	public List<Report> generateCartReport(@RequestParam int userId) {
		return reportService.getCartReport(userId);
	}

}
