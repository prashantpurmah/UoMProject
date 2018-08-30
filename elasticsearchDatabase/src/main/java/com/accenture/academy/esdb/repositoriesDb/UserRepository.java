package com.accenture.academy.esdb.repositoriesDb;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academy.esdb.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	

}
