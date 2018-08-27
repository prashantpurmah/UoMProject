package com.accenture.academy.esdb.services;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.accenture.academy.esdb.entities.Employee;
import com.accenture.academy.esdb.repositories.EmployeeRepository;
import com.accenture.academy.esdb.repositoriesDb.EmployeeDBRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	private EmployeeDBRepository employeeDBRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeDBRepository employeeDBRepository) {
		this.employeeRepository = employeeRepository;
		this.employeeDBRepository =  employeeDBRepository;
	}
	
	public Iterable<Employee> findByName(String name) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name)
        		.fuzziness(Fuzziness.TWO).boost(1.0f).prefixLength(0)
        		.fuzzyTranspositions(true);
        
		return employeeRepository.search(queryBuilder);
    }
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}
	
	public Employee getById(String employeeId) {
		return employeeRepository.findOne(employeeId);
	}
	
	public List<Employee> findAllFromDb(){
		return employeeDBRepository.findAll();
	}
	
	public Long countIndex(){
		return employeeRepository.count();
	}
	
	public Long countDB() {
		return employeeDBRepository.count();
	}
	

}
