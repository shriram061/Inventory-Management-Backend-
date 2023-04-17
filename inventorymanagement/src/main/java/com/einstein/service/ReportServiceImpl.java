package com.einstein.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einstein.model.Cart;
import com.einstein.model.CartItem;
import com.einstein.model.InventoryReport;
import com.einstein.model.Product;
import com.einstein.model.Report;
import com.einstein.repository.ProductRepository;
import com.einstein.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportRepository reportrepository;

	/**
	 * Sets the cart report for the given cart by iterating through the cart items
	 * and saving a Report object for each item in the ReportRepository.
	 * 
	 * @param cart The cart for which the report needs to be generated.
	 * 
	 * @return void
	 */
	@Override
	public void setCartReport(Cart cart) {
		for (CartItem items : cart.getCartItems()) {
			Report report = new Report();

			report.setPrice(items.getProduct().getPrice());
			report.setProductId(items.getProduct().getProductId());
			report.setProductName(items.getProduct().getProductName());
			report.setQuantity(items.getQuantity());
			report.setDate(LocalDateTime.now());
			report.setUserId(cart.getUserId());

			reportrepository.save(report);

		}

	}

	/**
	 * Retrieves the cart report for the given user from the ReportRepository.
	 * 
	 * @param userId The id of the user for which the cart report needs to be
	 *               retrieved.
	 * 
	 * @return A list of Report objects representing the cart report for the given
	 *         user.
	 */
	@Override
	public List<Report> getCartReport(int userId) {
		List<Report> report = reportrepository.findByUserId(userId);
		System.out.println("Load balancer check");
		return report;

	}

}
