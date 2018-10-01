package com.accenture.academy.esdb.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.accenture.academy.esdb.dtos.EmployeeScore;
import com.accenture.academy.esdb.entities.Employee;
import com.accenture.academy.esdb.repositories.EmployeeRepository;
import com.accenture.academy.esdb.repositoriesDb.EmployeeDBRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	private EmployeeDBRepository employeeDBRepository;
	private EntityMapper entityMapper;
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeDBRepository employeeDBRepository,
			EntityMapper entityMapper, ElasticsearchTemplate elasticsearchTemplate) {
		this.employeeRepository = employeeRepository;
		this.employeeDBRepository = employeeDBRepository;
		this.elasticsearchTemplate = elasticsearchTemplate;
		this.entityMapper = entityMapper;
	}

	/**
	 * @param name
	 * @return Iterable of Employees with probable scores attached
	 * 		   which match the input name
	 */
	public Iterable<EmployeeScore> findByName(String name) {
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name).fuzziness(Fuzziness.ONE).boost(1.0f)
				.prefixLength(0).fuzzyTranspositions(true);
		SearchQuery search = new NativeSearchQuery(queryBuilder);
		Pageable pageRequest = new PageRequest(0, 10);
		return elasticsearchTemplate.query(search, new ResultsExtractor<Page<EmployeeScore>>() {

			/* (non-Javadoc)
			 * @see org.springframework.data.elasticsearch.core.ResultsExtractor#extract(org.elasticsearch.action.search.SearchResponse)
			 * Method to extract hit score from search results for each employee
			 */
			@Override
			public Page<EmployeeScore> extract(SearchResponse response) {
				List<EmployeeScore> employees = new ArrayList<>();
				SearchHit[] hits = response.getHits().getHits();
				for (SearchHit hit : hits) {
					try{
						Employee employee = entityMapper.mapToObject(hit.getSourceAsString(), Employee.class);
						employees.add(new EmployeeScore(employee.getEmployeeId(),
														employee.getName(),
														hit.getScore()));
					} catch(IOException e) {
						System.out.println(e.getMessage());
					}

				}

				return new PageImpl<>(employees, pageRequest, response.getHits().getTotalHits());
			}

		});
	}

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getById(String employeeId) {
		return employeeRepository.findOne(employeeId);
	}

	public List<Employee> findAllFromDb() {
		return employeeDBRepository.findAll();
	}

	public Long countIndex() {
		return employeeRepository.count();
	}

	public Long countDB() {
		return employeeDBRepository.count();
	}

}
