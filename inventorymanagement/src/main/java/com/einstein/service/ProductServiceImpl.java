package com.einstein.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.einstein.model.Product;
import com.einstein.model.ProductStatus;

import com.einstein.model.User;
import com.einstein.repository.ProductRepository;
import com.einstein.repository.UserRepository;

@Service
@CacheConfig(cacheNames = "products")
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productrepository;

	@Autowired
	UserRepository userrepository;

	/**
	 * Saves a product to the database. If a product with the same name and price
	 * already exists, updates the quantity of the existing product.
	 * 
	 * @param product The product to be saved.
	 * 
	 * @return Returns the saved product.
	 */
	@Override
	@CacheEvict(allEntries = true)
	public Product saveProduct(Product product) {

		Product prod = productrepository.findByProductNameAndPrice(product.getProductName(), product.getPrice());
		if (prod != null) {
			prod.setQuantity(product.getQuantity() + prod.getQuantity());
			productrepository.save(prod);

		} else {

			if (product.getQuantity() < 0) {
				throw new IllegalArgumentException("Product quantity cannot be negative.");
			}
			if (product.getPrice() < 0) {
				throw new IllegalArgumentException("Product price cannot be negative.");

			}
			if (product.getQuantity() == 0) {
				product.setStatus(ProductStatus.EMPTY);

			} else if ((product.getQuantity() <= 4) && (product.getQuantity() > 0)) {
				product.setStatus(ProductStatus.LOWSTOCK);

			} else {
				product.setStatus(ProductStatus.INSTOCK);
			}
			productrepository.save(product);

		}
		return null;
	}

	/**
	 * Retrieves all products from the database.
	 * 
	 * @return Returns a list of all products.
	 */
	@Override
	@Cacheable
	public List<Product> getAllProduct() {
		System.out.println("load balance checking");
		System.out.println("calling from database");
		return productrepository.findAll();
	}

	/**
	 * Updates a product in the database.
	 * 
	 * @param products The updated product.
	 * 
	 * @param id       The ID of the product to be updated.
	 * 
	 * @return Returns the updated product.
	 */
	@Override
	@CacheEvict(allEntries = true)
	public Product updateProduct(Product products, int id) {
		Product product = productrepository.findByProductId(id);
		product.setProductId(products.getProductId());
		product.setProductName(products.getProductName());
		if (products.getPrice() < 0) {
			throw new IllegalArgumentException("Price cannot be negative.");

		} else {
			product.setPrice(products.getPrice());
		}
		if (products.getQuantity() < 0) {
			throw new IllegalArgumentException("Quantity cannot be negative.");
		} else {
			product.setQuantity(products.getQuantity());
		}
		product.setValidity(products.getValidity());

		if (products.getQuantity() == 0) {
			product.setStatus(ProductStatus.EMPTY);

		} else if ((products.getQuantity() <= 4) && (products.getQuantity() > 0)) {
			product.setStatus(ProductStatus.LOWSTOCK);

		} else {
			product.setStatus(ProductStatus.INSTOCK);
		}

		productrepository.save(product);
		return product;

	}

	/**
	 * Deletes a product from the database.
	 * 
	 * @param id The ID of the product to be deleted.
	 */
	@Override
	@CacheEvict(allEntries = true)
	public void deleteProduct(int id) {
		productrepository.deleteById(id);
	}

	/**
	 * Searches for products in the database that match a given keyword.
	 * 
	 * @param keyword The keyword to search for.
	 * 
	 * @return Returns a list of products that match the given keyword.
	 */
	@Override
	public List<Product> listAll(String keyword) {
		if (keyword != null) {
			return productrepository.search(keyword);
		}

		return productrepository.findAll();
	}

	/**
	 * Lists all products or products with a specific ID.
	 * 
	 * @param id the ID of the product to search for. If 0, return all products.
	 * 
	 * @return a list of products with the given ID, or all products if ID is 0.
	 */
	@Override
	public List<Product> listAllId(int id) {

		if (id != 0) {
			return productrepository.searchId(id);
		}
		return productrepository.findAll();
	}

	/**
	 * Returns a product with the given ID.
	 * 
	 * @param id the ID of the product to retrieve.
	 * 
	 * @return the product with the given ID, or null if not found.
	 */
	@Override
	@Cacheable(key="#id")
	public Product viewProductById(int id) {
		System.out.println("calling from database");
		return productrepository.findByProductId(id);
	}

	/**
	 * Returns an Optional containing the product with the given ID, or an empty
	 * Optional if not found.
	 * 
	 * @param id the ID of the product to retrieve.
	 * 
	 * @return an Optional containing the product with the given ID, or an empty
	 *         Optional if not found.
	 */
	@Override
	public Optional<Product> view(int id) {

		return productrepository.findById(id);
	}

}
