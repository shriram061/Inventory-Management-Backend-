package com.einstein.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.einstein.report.feign.ReportFeign;
import com.einstein.report.model.InventoryReport;
import com.einstein.report.model.Product;
import com.einstein.report.model.Report;
import com.einstein.report.repository.ProductRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	ProductRepository productrepository;
	@Autowired
	ReportFeign reportfeign;

	/**
	 * 
	 * Generates an inventory report by calling the ReportFeign client to retrieve a
	 * list of products and
	 * 
	 * calculating the total number of products and total stock. Uses circuit
	 * breaker pattern to handle failures.
	 * 
	 * @return the inventory report object containing total number of products,
	 *         total stock and the list of products
	 */
	@Override
	@CircuitBreaker(name = "generateInventoryReport", fallbackMethod = "generateInventoryReportFallback")
	public InventoryReport generateInventoryReport() {
		List<Product> products = reportfeign.getReport();
		int totalProducts = products.size();
		int totalStock = 0;
		for (Product product : products) {
			totalStock += product.getQuantity();
		}
		return new InventoryReport(totalProducts, totalStock, products);
	}

	/**
	 * 
	 * Fallback method for generating the inventory report in case of failure.
	 * Retrieves the list of products from the
	 * 
	 * ProductRepository and calculates the total number of products and total
	 * stock.
	 * 
	 * @param t the throwable object that caused the failure
	 * 
	 * @return the inventory report object containing total number of products,
	 *         total stock and the list of products
	 */
	public InventoryReport generateInventoryReportFallback(Throwable t) {
		List<Product> products = productrepository.findAll();
		int totalProducts = products.size();
		int totalStock = 0;
		for (Product product : products) {
			totalStock += product.getQuantity();
		}
		return new InventoryReport(totalProducts, totalStock, products);
	}

}
