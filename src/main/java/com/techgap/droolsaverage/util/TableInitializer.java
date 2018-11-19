package com.techgap.droolsaverage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TableInitializer {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TableInitializer(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public void createTables() {
		System.out.println("Creating tables...");
		jdbcTemplate.execute("CREATE TABLE users (username TEXT NOT NULL PRIMARY KEY, password TEXT NOT NULL, enabled BOOLEAN NOT NULL);");
		
		jdbcTemplate.execute("CREATE TABLE employee_metrics(employee_code SERIAL NOT NULL PRIMARY KEY, kpi1 DECIMAL NOT NULL, kpi2 DECIMAL "
				+ "			  NOT NULL, month INTEGER NOT NULL, year INTEGER NOT NULL, kpi3 DECIMAL NOT NULL, kpi4 DECIMAL NOT NULL, kpi_tot DECIMAL "
				+ "			  NOT NULL, name text, is_draft BOOLEAN);");
		
		jdbcTemplate.execute("CREATE TABLE rule_files (name TEXT NOT NULL PRIMARY KEY, location TEXT NOT NULL, month INTEGER NOT NULL, year INTEGER NOT NULL, rules TEXT NOT NULL, csv TEXT NOT NULL);");
		jdbcTemplate.execute("INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$AXQmICgFYVflZicfkG2Qj.QKaq7HAS3Uvn7Ua9f5rv3tv5Kc0om2K', true);");
		jdbcTemplate.execute("INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER') ON CONFLICT (username) DO NOTHING;");
		jdbcTemplate.execute("INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN') ON CONFLICT (username) DO NOTHING;");
		System.out.println("... done creating tables");
	}
}
