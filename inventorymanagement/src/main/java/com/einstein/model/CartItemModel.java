package com.einstein.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemModel {

	private int quantity;

	private int userId;

	private int productId;

}