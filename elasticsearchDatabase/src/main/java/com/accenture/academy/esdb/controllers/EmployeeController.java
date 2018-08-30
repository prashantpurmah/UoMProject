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
	public List<Employee> getEmployeeByName(@PathVariable String name){
		Iterable<Employee> employees = employeeService.findByName(name);
		List<Employee> employeesList = new ArrayList<>();
		employees.forEach(x -> employeesList.add(x));
		return employeesList;
	}
	
	@PostMapping
	public Employee insertEmployee(@RequestBody Employee employee) {
	  return employeeService.save(employee);
	}
	
	@GetMapping("/{employeeId}/delete")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable String employeeId){
		System.out.println(employeeId);
		Employee employee = employeeService.getById(employeeId);
		System.out.println(employee);
		if(employee!=null) {
			employeeService.delete(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping
	public String home() {
		return "Successful";
	}
	
}
