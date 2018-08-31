package com.accenture.academy.esdb.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.accenture.academy.esdb.entities.Employee;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, String>{
	
	Iterable<Employee> findByName(String name);
	
	Page<Employee> findByName(String name, Pageable pageable);

}
