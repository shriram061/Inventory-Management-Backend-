package com.einstein.service;

import java.util.List;
import java.util.Optional;


import com.einstein.model.Product;

import com.einstein.model.User;

public interface ProductService {

	public Product saveProduct(Product product);
	public List<Product> getAllProduct();
	public Product viewProductById(int id); 
	public Optional<Product> view(int id); 
	public Product updateProduct(Product products,int id);
	public void deleteProduct (int id);
	List<Product> listAll(String keyword);
	List<Product> listAllId(int id);
}
