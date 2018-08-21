package com.accenture.academy.esdb.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academy.esdb.dao.EmployeeDao;
import com.accenture.academy.esdb.entities.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeDao employeeDao;

	public EmployeeController(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> getBookById(@PathVariable String id){
	  return employeeDao.getEmployeeById(id);
	}
	
	@PostMapping
	public Employee insertEmployee(@RequestBody Employee employee) throws Exception {
	  return employeeDao.insertEmployee(employee);
	}
	
}
