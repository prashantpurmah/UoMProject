package com.accenture.academy.esdb.services;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.academy.esdb.entities.CustomUserDetails;
import com.accenture.academy.esdb.entities.User;
import com.accenture.academy.esdb.repositoriesDb.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * @param employeeId
	 * @param password
	 * @return whether credentials are valid or not
	 */
	/*public String validateCredentials(String employeeId,  String password) {
		
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
		
	}*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOne(username);

        if(user == null) {
        	throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmployeeId(), user.getPassword(), Collections.emptyList());
		
	}
	
}
