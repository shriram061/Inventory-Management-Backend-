package com.authentication.einstein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.authentication.einstein.model.PersonalDetails;

@Repository
public interface PersonalDetailsRepo extends JpaRepository<PersonalDetails, String> {

	@Query(value = "SELECT * FROM user_details p WHERE p.user_name = ?1", nativeQuery = true)
	public PersonalDetails getUserByUserName(String username);

}
