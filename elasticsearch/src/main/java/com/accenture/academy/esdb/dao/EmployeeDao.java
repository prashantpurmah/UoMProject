package com.accenture.academy.esdb.dao;

import java.util.Map;
import java.util.UUID;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

import com.accenture.academy.esdb.entities.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class EmployeeDao {

	private final String INDEX = "employeedata";
	private final String TYPE = "employees";  
	private RestHighLevelClient restHighLevelClient;
	private ObjectMapper objectMapper;
	
	public EmployeeDao(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper) {
		this.restHighLevelClient = restHighLevelClient;
		this.objectMapper = objectMapper;
	}
	
	public Employee insertEmployee(Employee employee){
		  
		  Map dataMap = objectMapper.convertValue(employee, Map.class);
		  IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, employee.getEmployeeId())
		                .source(dataMap);
		  System.out.println(employee);
		  try {
		    IndexResponse response = restHighLevelClient.index(indexRequest);
		  } catch(ElasticsearchException e) {
		    e.getDetailedMessage();
		  } catch (java.io.IOException ex){
		    ex.getLocalizedMessage();
		  }
		  return employee;
	}
	
	public Map<String, Object> getEmployeeById(String id){
		  GetRequest getRequest = new GetRequest(INDEX, TYPE, id); //returning null
		  System.out.println(getRequest);
		  GetResponse getResponse = null;
		  try {
		    getResponse = restHighLevelClient.get(getRequest);
		  } catch (java.io.IOException e){
		    e.getLocalizedMessage();
		  }
		  Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		  return sourceAsMap;
	}
	
	
	
	
	
	
}
