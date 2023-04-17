package com.einstein.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.einstein.model.Cart;
import com.einstein.model.CartItem;
import com.einstein.model.CartItemModel;
import com.einstein.model.Product;
import com.einstein.model.ProductStatus;
import com.einstein.model.User;
import com.einstein.repository.CartItemRepo;
import com.einstein.repository.CartRepository;
import com.einstein.repository.ProductRepository;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartItemRepo cartItemRepo;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	JavaMailSender javamailsender;

	/**
	 * 
	 * Adds an item to the cart for the specified user.
	 * 
	 * @param Item the CartItemModel object containing the item details to be added
	 *             to the cart
	 */

	public void AddCartItem(CartItemModel Item) {

		Cart cart = cartRepository.findByUserId(Item.getUserId());
		if (cart != null) {
			int flag = 0;
			for (CartItem crtitem : cart.getCartItems()) {
				if (crtitem.getProduct().getProductId() == Item.getProductId()) {
					flag = 1;
					int currentQuantity = crtitem.getProduct().getQuantity(); // Get the current quantity
					int newQuantity = currentQuantity - Item.getQuantity(); // Reduce the quantity
					crtitem.getProduct().setQuantity(newQuantity);
					if (newQuantity == 0) {

						crtitem.getProduct().setStatus(ProductStatus.EMPTY);
						sendEmail(crtitem.getProduct());

					} else if ((newQuantity <= 4) && (newQuantity > 0)) {
						crtitem.getProduct().setStatus(ProductStatus.LOWSTOCK);
						sendEmail(crtitem.getProduct());

					} else {
						crtitem.getProduct().setStatus(ProductStatus.INSTOCK);
					} // Set the new quantity
					if (crtitem.getProduct().getQuantity() < 0) {
						throw new IllegalArgumentException("Product quantity cannot be negative.");
					}
					if (crtitem.getProduct().getPrice() < 0) {
						throw new IllegalArgumentException("Product price cannot be negative.");

					}

					crtitem.setQuantity(crtitem.getQuantity() + Item.getQuantity());
					cartItemRepo.save(crtitem);
				}
			}
			if (flag == 0) {
				CartItem cartit = new CartItem();
				Optional<Product> product = productRepository.findById(Item.getProductId());
				if (product.isPresent()) {
					Product productObj = product.orElseThrow(); // Get the Product object
					int currentQuantity = productObj.getQuantity(); // Get the current quantity
					int newQuantity = currentQuantity - Item.getQuantity(); // Reduce the quantity
					productObj.setQuantity(newQuantity);
					if (newQuantity == 0) {

						productObj.setStatus(ProductStatus.EMPTY);
						sendEmail(product);

					} else if ((newQuantity <= 4) && (newQuantity > 0)) {
						productObj.setStatus(ProductStatus.LOWSTOCK);
						sendEmail(product);

					} else {
						productObj.setStatus(ProductStatus.INSTOCK);
					} // Set the new quantity
					if (productObj.getQuantity() < 0) {
						throw new IllegalArgumentException("Product quantity cannot be negative.");
					}
					if (productObj.getPrice() < 0) {
						throw new IllegalArgumentException("Product price cannot be negative.");

					}
					cartit.setProduct(productObj);
				}

				cartit.setQuantity(Item.getQuantity());
				cartit.setCart(cart);
				cartItemRepo.save(cartit);

			}

		} else {
			Cart cartt = new Cart();
			cartt.setUserId(Item.getUserId());
			cartRepository.save(cartt);
			CartItem cartItem = new CartItem();
			cartItem.setCart(cartt);

			Optional<Product> product = productRepository.findById(Item.getProductId());
			if (product.isPresent()) {
				Product productObj = product.orElseThrow(); // Get the Product object
				int currentQuantity = productObj.getQuantity(); // Get the current quantity
				int newQuantity = currentQuantity - Item.getQuantity(); // Reduce the quantity
				productObj.setQuantity(newQuantity);
				if (newQuantity == 0) {

					productObj.setStatus(ProductStatus.EMPTY);
					sendEmail(product);

				} else if ((newQuantity <= 4) && (newQuantity > 0)) {
					productObj.setStatus(ProductStatus.LOWSTOCK);
					sendEmail(product);

				} else {
					productObj.setStatus(ProductStatus.INSTOCK);
				} // Set the new quantity
				if (productObj.getQuantity() < 0) {
					throw new IllegalArgumentException("Product quantity cannot be negative.");
				}
				if (productObj.getPrice() < 0) {
					throw new IllegalArgumentException("Product price cannot be negative.");

				}
				cartItem.setProduct(productObj);
			}

			cartItem.setQuantity(Item.getQuantity());
			cartItemRepo.save(cartItem);
		}
	}

	/**
	 * @return void
	 * 
	 *         Sends an email to the owner to remind them to reorder a low stock
	 *         product. If a product is provided as an Optional, its information is
	 *         included in the email. If a product object is provided, its
	 *         information is included in the email.
	 * 
	 * @param product    an Optional<Product> representing the product to be
	 *                   reordered (if present)
	 * @param productObj a Product object representing the product to be reordered
	 *                   (if product is not present in the Optional)
	 * @throws NoSuchElementException if the product is not present in the Optional
	 */

	/**
	 * Sends an email to the owner to remind them to reorder a low stock product.
	 * 
	 * @param product an Optional<Product> representing the product to be reordered
	 *                (if present)
	 * @throws NoSuchElementException if the product is not present in the Optional
	 */
	public void sendEmail(Optional<Product> product) {
		Product productObj = product.orElseThrow();
		String ownerEmail = "vigneshshriram5@gmail.com";
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(ownerEmail);
		mailMessage.setSubject("Product Low Stock Reminder");
		mailMessage.setText("Dear ADMIN" + ",\n\n" + "Product Named:- " + productObj.getProductName() + ", Pricing: ₹"
				+ productObj.getPrice() + ", is in quantity: " + productObj.getQuantity()
				+ " and therefore the status of the product is " + productObj.getStatus()
				+ ". Please reorder the product, to gain the stocks.");
		javamailsender.send(mailMessage);
	}

	/**
	 * Sends an email to the owner to remind them to reorder a low stock product.
	 * 
	 * @param productObj a Product object representing the product to be reordered
	 */
	public void sendEmail(Product productObj) {
		String ownerEmail = "vigneshshriram5@gmail.com";
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(ownerEmail);
		mailMessage.setSubject("Product Low Stock Reminder");
		mailMessage.setText("Dear ADMIN" + ",\n\n" + "Product Named:- " + productObj.getProductName() + ", Pricing: ₹"
				+ productObj.getPrice() + ", is in quantity: " + productObj.getQuantity()
				+ " and therefore the status of the product is " + productObj.getStatus()
				+ ". Please reorder the product, to gain the stocks.");
		javamailsender.send(mailMessage);
	}

}