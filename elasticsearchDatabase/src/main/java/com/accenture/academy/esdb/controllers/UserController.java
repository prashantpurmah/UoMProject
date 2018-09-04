package com.accenture.academy.esdb.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academy.esdb.dtos.UserDto;
import com.accenture.academy.esdb.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<String> validateUser(@Valid @RequestBody UserDto userDto){
		String result = userService.validateCredentials(userDto.getEmployeeId(), userDto.getPassword());
		if(result.equals("valid")) {
			return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
		}
		if(result.equals("invalid password")) {
			return new ResponseEntity<String>(result, HttpStatus.UNAUTHORIZED);
		}
		if(result.equals("invalid userNotFound")) {
			return new ResponseEntity<String>(result, HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<String>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
