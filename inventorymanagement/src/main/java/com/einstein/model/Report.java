package com.einstein.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int productId;
	private String productName;
	private int quantity;
	private int price;
	private int userId;
	private LocalDateTime date;
}
