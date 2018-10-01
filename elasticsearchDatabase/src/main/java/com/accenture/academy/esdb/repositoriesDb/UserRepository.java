package com.accenture.academy.esdb.repositoriesDb;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academy.esdb.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByEmployeeId(String employeeId);
}
