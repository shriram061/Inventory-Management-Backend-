package com.einstein.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.einstein.model.User;
import com.einstein.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepository;

	/**
	 * Retrieves all the User objects from the database.
	 * 
	 * @return the list of all User objects in the database
	 */
	public List<User> getAllUsers() {
		return userrepository.findAll();
	}

	/**
	 * Saves the given User object to the database.
	 * 
	 * @param prop the User object to save
	 * @return the saved User object
	 */
	public User saveToUserByUser(User prop) {
		return userrepository.save(prop);
	}

	/**
	 * Retrieves the User object with the given userId from the database.
	 * 
	 * @param userId the userId of the User object to retrieve
	 * @return the User object with the given userId, or null if it doesn't exist
	 */
	public User getUserByUserId(int userId) {
		return userrepository.findByUserId(userId);
	}

	/**
	 * Updates the User object with the given id in the database using the
	 * UserRepository.
	 * 
	 * @param id      the id of the User object to update
	 * @param userReq the User object containing the new values for the User object
	 * @return the updated User object
	 * @throws RuntimeException if the User object with the given id does not exist
	 */
	public User updateUser(int id, User userReq) {
		Optional<User> optuser = userrepository.findById(id);
		if (!optuser.isPresent()) {
			throw new RuntimeException("user with id " + id + "does not exist");
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = optuser.get();
		if (userReq.getUserName() != null) {
			user.setUserName(userReq.getUserName());
			user.setEmailId(userReq.getEmailId());
		}
		if (userReq.getPassword() != null) {
			String encodedPassword = passwordEncoder.encode(userReq.getPassword());
			user.setPassword(encodedPassword);
		}
		userrepository.save(user);
		return user;
	}

	/**
	 * Deletes the User object with the given id from the database.
	 * 
	 * @param id the id of the User object to delete
	 * @return the deleted User object
	 */
	public User deleteUser(int id) {
		User user = userrepository.findByUserId(id);
		userrepository.deleteById(id);
		return user;
	}

	/**
	 * Retrieves the User object with the given username from the database.
	 * 
	 * @param username the username of the User object to retrieve
	 * @return the User object with the given username, or null if it doesn't exist
	 */
	public User getUserByUserName(String username) {
		return userrepository.getUserByUserName(username);
	}

}