package com.einstein.service;

import java.util.List;

import com.einstein.model.ContactUs;

public interface ContactUsService {
	public ContactUs saveQuery(ContactUs contact);
	public List<ContactUs> getAllQueries();
	public ContactUs viewQueryById(int id);
	public void deleteProduct(int id); 
}
