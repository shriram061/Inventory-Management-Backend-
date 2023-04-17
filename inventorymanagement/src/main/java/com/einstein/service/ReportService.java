package com.einstein.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.einstein.model.Cart;
import com.einstein.model.InventoryReport;
import com.einstein.model.Report;

public interface ReportService {
	void setCartReport(Cart cart);

	List<Report> getCartReport(int userId);
}
