package com.accenture.academy.esdb.services;

import org.springframework.stereotype.Service;

import com.accenture.academy.esdb.entities.User;
import com.accenture.academy.esdb.repositoriesDb.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public String validateCredentials(String employeeId,  String password) {
		
		User user = userRepository.findOne(employeeId);
		if (user != null) {
			if(user.getPassword().equals(password)) {
				return "valid";
			} else {
				return "invalid password";
			}
		} else {
			return "invalid userNotFound";
		}
		
	}
	
}
