package com.einstein.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einstein.model.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {

	List<CartItem> findByCartId(Long id);

}