package com.accenture.academy.esdb.repositoriesDb;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.academy.esdb.entities.Employee;

public interface EmployeeDBRepository extends JpaRepository<Employee, String>{

}
