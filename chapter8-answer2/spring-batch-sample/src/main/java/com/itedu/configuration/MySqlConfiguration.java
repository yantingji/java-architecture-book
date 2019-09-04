package com.itedu.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MySqlConfiguration {
	@Value("${batch.mysql.jdbc.driver}")
	private String driverClassName;

	@Value("${batch.mysql.jdbc.url}")
	private String driverUrl;

	@Value("${batch.mysql.jdbc.user}")
	private String driverUsername;

	@Value("${batch.mysql.jdbc.password}")
	private String driverPassword;

	@Bean
	public DataSource mysqlDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(driverUrl);
		dataSource.setUsername(driverUsername);
		dataSource.setPassword(driverPassword);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager mysqlTransactionManager() {
		return new DataSourceTransactionManager(mysqlDataSource());
	}
}
