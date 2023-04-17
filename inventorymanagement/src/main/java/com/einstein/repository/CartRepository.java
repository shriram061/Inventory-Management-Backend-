package com.einstein.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einstein.model.Cart;
import com.einstein.model.Product;
import com.einstein.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findByUserId(int id);
}
