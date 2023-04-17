package com.einstein.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.einstein.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Product findByProductName(String name);

	public Product findByProductId(int id);

	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword%")
	List<Product> search(@Param("keyword") String keyword);

	@Query("SELECT p FROM Product p WHERE p.productId = :id")
	List<Product> searchId(@Param("id") int id);

	public Product findByProductNameAndPrice(String productName, int price);

}