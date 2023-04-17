package com.einstein.report.model;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

	private int id;
	private int productId;
	private String productName;
	private int quantity;
	private int price;
	private int userId;
	private LocalDateTime date;
}
