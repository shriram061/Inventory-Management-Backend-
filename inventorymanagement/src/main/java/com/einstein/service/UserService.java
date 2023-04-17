package com.einstein.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.einstein.model.User;

@Service
public interface UserService {
	List<User> getAllUsers();

	User saveToUserByUser(User user);

	User getUserByUserId(int userId);

	User deleteUser(int id);

	User updateUser(int id, User user);

	User getUserByUserName(String username);
}
