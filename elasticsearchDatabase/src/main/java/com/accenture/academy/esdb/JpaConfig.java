package com.accenture.academy.esdb;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableJpaRepositories({"com.accenture.academy.esdb.repositoriesDb"})
public class JpaConfig {
	
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/elasticsearch");
		dataSource.setUsername("root");
		dataSource.setPassword("mankush007");
		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("useSSL", "true");
		connectionProperties.setProperty("verifyServerCertificate", "false");
		dataSource.setConnectionProperties(connectionProperties);
		
		return dataSource;
		
	}

}
