package com.einstein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einstein.model.ContactUs;
@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {
	public ContactUs findByname(String name);

	public ContactUs findById(int id);
}
