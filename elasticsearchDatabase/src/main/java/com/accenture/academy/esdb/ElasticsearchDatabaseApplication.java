package com.accenture.academy.esdb;

import java.util.List;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.accenture.academy.esdb.entities.Employee;
import com.accenture.academy.esdb.services.EmployeeService;

@SpringBootApplication
public class ElasticsearchDatabaseApplication implements CommandLineRunner{
	
	@Autowired
	private ElasticsearchOperations es;

	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchDatabaseApplication.class, args);
	}
	
	//useful for debug
    private void printElasticSearchInfo() {

        System.out.println("<--ElasticSearch--");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("--ElasticSearch-->");
    }



	@Override
	public void run(String... args) throws Exception {
		printElasticSearchInfo();
		System.out.println(refreshIndex());
		
	}
	
	private String refreshIndex() {
		final long start = System.currentTimeMillis();
		if(employeeService.countIndex() != employeeService.countDB()) {
			es.deleteIndex(Employee.class);
			es.createIndex(Employee.class);
			List<Employee> employeesList = employeeService.findAllFromDb();
			for (Employee employee : employeesList) {
				employeeService.save(employee);
			}
			final long runningTime = System.currentTimeMillis() - start;
			return "Time taken to refresh index = "+ runningTime/1000.0 + "s";
		} else {
			return "Index up to date";
		}
	}
	
}
