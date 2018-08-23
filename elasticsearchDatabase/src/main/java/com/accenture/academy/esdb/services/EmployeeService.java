package com.accenture.academy.esdb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.accenture.academy.esdb.entities.Employee;
import com.accenture.academy.esdb.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public Iterable<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	

}
