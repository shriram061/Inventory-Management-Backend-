package com.einstein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.einstein.model.ContactUs;
import com.einstein.model.Product;
import com.einstein.repository.ContactUsRepository;
@Service
@CacheConfig(cacheNames = "ContactUss")
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	ContactUsRepository contactrepository;
	
	@Autowired
	JavaMailSender javamailsender;
	
	
	
	/**
	 * Saves a query to the database. 
	 * 
	 * @param contact The query to be saved.
	 * 
	 * @return Returns the saved query.
	 */
	@Override
	@CacheEvict(allEntries = true)
	public ContactUs saveQuery(ContactUs contact) {
		ContactUs query = contactrepository.save(contact);
		sendEmail(query);
		return query;
	}
	/**
	 * Retrieves all query from the database.
	 * 
	 * @return Returns a list of all queries.
	 */
	@Override
	@Cacheable
	public List<ContactUs> getAllQueries() {
		// TODO Auto-generated method stub
		return contactrepository.findAll();
	}
	
	/**
	 * Lists all query or query with a specific ID.
	 * 
	 * @param id the ID of the query to search for. If 0, return all query.
	 * 
	 *  @return the query with the given ID, or null if not found.
	 */
	@Override
	@Cacheable(key="#id")
	public ContactUs viewQueryById(int id) {
		// TODO Auto-generated method stub
		System.out.println("calling from database");
		return contactrepository.findById(id);
	}
	/**
	 * Deletes a query from the database.
	 * 
	 * @param id The ID of the query to be deleted.
	 */
	@Override
	@CacheEvict(allEntries = true)
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		contactrepository.deleteById(id);
	}
	
	/**
	 * 
	 * Sends a message confirmation email to the user .
	 * 
	 * @param contact The message containing the details of the user.
	 */
	public void sendEmail(ContactUs contact) {

		String ownerEmail = contact.getEmailId();
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(ownerEmail);
		mailMessage.setSubject("Thank you for connecting!");
		mailMessage.setText("Dear " + contact.getName()+",\n\n" + "We have recieved your message"+".\n\n"+
		contact.getQuestion()+".\n\n"+
		"We will soon contact you soon,"+".\n\n"+"Thank you for connecting with us.");
		javamailsender.send(mailMessage);
	}

}
