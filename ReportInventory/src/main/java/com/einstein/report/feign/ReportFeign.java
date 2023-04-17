
package com.einstein.report.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.einstein.report.model.CartItem;
import com.einstein.report.model.InventoryReport;
import com.einstein.report.model.Product;
import com.einstein.report.model.Report;

@FeignClient(name = "inventory-management")
//@FeignClient(name="clientFeign",url="http://localhost:9876")
public interface ReportFeign {

	@GetMapping("/cart/all")
	public List<CartItem> getallCartItems(@RequestParam int userId);

	@GetMapping("/report")
	public List<Product> getReport();

	@GetMapping("/cartreport")
	public List<Report> generateCartReport(@RequestParam int userId);

}
