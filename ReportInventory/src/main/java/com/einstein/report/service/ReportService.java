package com.einstein.report.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.einstein.report.model.InventoryReport;
import com.einstein.report.model.Report;

public interface ReportService {

	public InventoryReport generateInventoryReport();

}
