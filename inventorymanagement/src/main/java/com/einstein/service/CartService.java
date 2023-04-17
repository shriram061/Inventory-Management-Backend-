package com.einstein.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.einstein.model.Cart;
import com.einstein.model.CartItem;
import com.einstein.model.CartItemModel;
import com.einstein.model.Report;
import com.einstein.model.User;
import com.einstein.repository.CartItemRepo;
import com.einstein.repository.CartRepository;
import com.einstein.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepo cartItemRepo;

	@Autowired
	private ReportService reportservice;

	@Autowired
	JavaMailSender javamailsender;

	@Autowired
	UserRepository userrepo;

	/**
	 * Adds an item to the user's cart.
	 * 
	 * @param Item The CartItemModel object containing details of the item to be
	 *             added.
	 * 
	 * @return A string message indicating if the item was added successfully.
	 */
	public String addItemToCart(CartItemModel Item) {
		Cart cart = cartRepository.findByUserId(Item.getUserId());
		List<CartItem> cartItems = cartItemRepo.findByCartId(cart.getId());
		cart.setCartItems(cartItems);
		int total = (int) 0;
		for (CartItem citem : cartItems) {
			total += citem.getProduct().getPrice();
		}
		cart.setPrice(total);
		cartRepository.save(cart);

		return "Added Items sucessfully";
	}

	/**
	 * Retrieves all cart items for the given user.
	 * 
	 * @param userId The ID of the user whose cart items are to be retrieved.
	 * 
	 * @return A list of CartItem objects belonging to the user.
	 */
	public List<CartItem> getallCartItems(int userId) {
		Cart cart = cartRepository.findByUserId(userId);
		List<CartItem> cartItems = new ArrayList<>();
		cartItems = cart.getCartItems();
		return cartItems;
	}

	/**
	 * Checks out the user's cart and sends a checkout email to the user and admin.
	 * 
	 * @param userId The ID of the user whose cart is to be checked out.
	 * 
	 * @param report The Report object containing the details of the cart items.
	 * 
	 * @return A string message indicating if the checkout was successful.
	 */
	public String proceedToCheckout(int userId, Report report) {
		Cart cart = cartRepository.findByUserId(userId);

		if (cart == null || cart.getCartItems() == null) {
			return "Cart is Empty";
		}
		sendCheckoutEmail(report);
		reportservice.setCartReport(cart);
		cartRepository.deleteById(cart.getId());
		return "Cart Checkedout";
	}

	/**
	 * 
	 * Creates a cart for the given user.
	 * 
	 * @param userId The ID of the user for whom the cart is to be created.
	 */
	public void createCart(int userId) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		cartRepository.save(cart);

	}

	/**
	 * 
	 * Sends a checkout confirmation email to the user and admin.
	 * 
	 * @param report The Report object containing the details of the cart items.
	 */
	public void sendCheckoutEmail(Report report) {

		User emailIdObject = userrepo.findById(report.getUserId()).orElse(null);
		if (emailIdObject != null) {
			String executiveEmail = (emailIdObject != null) ? emailIdObject.getEmailId() : null;

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(executiveEmail);
			mailMessage.setSubject("Product CheckedOut");
			mailMessage.setText(
					"Dear " + emailIdObject.getUserName() + ",\n\n" + "The items have been Successfully checked out. "
							+ " Please deliver the product, safe and securely. Thanks ;)");
			javamailsender.send(mailMessage);
			String ADMIN = "vigneshshriram5@gmail.com";
			SimpleMailMessage mailMessage1 = new SimpleMailMessage();
			mailMessage1.setTo(ADMIN);
			mailMessage1.setSubject("Product Dispatched");
			mailMessage1.setText("Dear ADMIN" + ",\n\n" + "The items have been Successfully checked out, "
					+ "and will be delivered by the Executive Mr/Mrs. " + emailIdObject.getUserName()
					+ ". Please verify the report for the further product details. Thanks ;)");
			javamailsender.send(mailMessage1);
		}

	}
}
