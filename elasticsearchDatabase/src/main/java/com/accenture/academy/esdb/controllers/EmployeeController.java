package com.accenture.academy.esdb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academy.esdb.dtos.EmployeeScore;
import com.accenture.academy.esdb.entities.Employee;
import com.accenture.academy.esdb.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/**
	 * @param name of employee from Reader
	 * @return List of employee and the respective probability score
	 */
	@GetMapping("/{name}")
	public List<EmployeeScore> getEmployeeByName(@PathVariable String name){
		Iterable<EmployeeScore> employees = employeeService.findByName(name);
		List<EmployeeScore> employeesList = new ArrayList<>();
		employees.forEach(x -> employeesList.add(x));
		return employeesList;
	}
	
	
}
