package com.example.enoca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.enoca.entities.User;
import com.example.enoca.repository.UserRepository;


@Service
public class UserService {

	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}

	public User getById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateUser(Long userId, User newUser) {
		Optional <User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User updatedUser = user.get();
			updatedUser.setUserName(newUser.getUserName());
			updatedUser.setPassword(newUser.getPassword());
			userRepository.save(updatedUser);
			return updatedUser;
		}else
			return null;
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);		
	}

	
}

