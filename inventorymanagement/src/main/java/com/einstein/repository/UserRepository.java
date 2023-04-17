package com.einstein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.einstein.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUserId(Integer userId);

	@Query(value = "SELECT * FROM user p WHERE p.user_name = ?1", nativeQuery = true)
	public User getUserByUserName(String username);

	public User getEmailByEmailId(String emailId);
}