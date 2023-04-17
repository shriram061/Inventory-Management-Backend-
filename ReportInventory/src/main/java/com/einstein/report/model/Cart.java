package com.einstein.report.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	private Long id;

	private int userId;

	private int price;
	@JsonIgnore

	private List<CartItem> cartItems;
}
