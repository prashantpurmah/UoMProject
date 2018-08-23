package com.accenture.academy.esdb.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "employeedata", type = "employees")
public class Employee {
	
	@Id
	private String employeeId;
	private String name;
	
	public Employee() {	}

	public Employee(String employeeId, String name) {
		this.employeeId = employeeId;
		this.name = name;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + "]";
	}

	
	

}
