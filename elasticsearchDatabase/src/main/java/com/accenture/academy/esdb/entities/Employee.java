package com.accenture.academy.esdb.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Table(name="employees")
@Document(indexName = "employeedata", type = "employees")
public class Employee implements Serializable{
	
	@Id
	@javax.persistence.Id
	private String employeeId;
	private String name;
	
	@OneToMany(mappedBy = "employee")
	private Set<EmployeeAsset> employeeAssets;
	
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

	public Set<EmployeeAsset> getEmployeeAssets() {
		return employeeAssets;
	}

	public void setEmployeeAssets(Set<EmployeeAsset> employeeAssets) {
		this.employeeAssets = employeeAssets;
	}

	@Override
	public String toString() {
		return "{'employeeId':" + employeeId + ", 'name':" + name + "}";
	}

	
	

}
