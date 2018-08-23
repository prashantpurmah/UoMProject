package com.accenture.academy.esdb.controllers;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academy.esdb.entities.Employee;
import com.accenture.academy.esdb.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/{name}")
	public Iterable<Employee> getEmployeeByName(@PathVariable String name){
	  return employeeService.findByName(name);
	}
	
	@PostMapping
	public Employee insertEmployee(@RequestBody Employee employee) {
	  return employeeService.save(employee);
	}
	
}
