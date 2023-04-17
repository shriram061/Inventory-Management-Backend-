package com.einstein.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.einstein.model.CartItem;
import com.einstein.model.CartItemModel;
import com.einstein.model.Report;
import com.einstein.service.CartItemService;
import com.einstein.service.CartService;

@RestController
@CrossOrigin(origins = "*")

public class CartController {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CartService cartService;

	@PostMapping("/cart/addProduct")
	public String addItemToCart(@RequestBody CartItemModel product) {
		cartItemService.AddCartItem(product);
		return cartService.addItemToCart(product);
	}

	@GetMapping("/cart/all")
	public List<CartItem> getallCartItems(@RequestParam int userId) {
		return cartService.getallCartItems(userId);
	}

	@PostMapping("/cart/checkout")
	public String proceedToCheckout(@RequestParam int userId, Report report) {
		return cartService.proceedToCheckout(userId, report);
	}

}
