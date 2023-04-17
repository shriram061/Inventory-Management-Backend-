package com.einstein.service;

import org.springframework.stereotype.Service;

import com.einstein.model.CartItemModel;

@Service
public interface CartItemService {

	void AddCartItem(CartItemModel cartItem);

}