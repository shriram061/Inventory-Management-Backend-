package com.einstein.report.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private int price;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private String validity;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ProductStatus status = ProductStatus.DUMMY;

}