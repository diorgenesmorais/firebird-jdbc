package com.dms.firebird.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@EnableAutoConfiguration
@Configuration
public class DbConfig {

	private String url = "jdbc:firebirdsql:192.168.102.119/3050:c:/users/laudeci/downloads/CLIPP.FDB?charSet=utf8";

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.firebirdsql.jdbc.FBDriver");
		dataSource.setUrl(url);
		dataSource.setUsername("sysdba");
		dataSource.setPassword("masterkey");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
