package com.techgap.droolsaverage.droolsaverage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techgap.droolsaverage.util.AssetDAO;

@SpringBootApplication
@ComponentScan(basePackages = { "com.techgap.droolsaverage.controller", "com.techgap.droolsaverage.model",
		"com.techgap.droolsaverage.util", "com.techgap.droolsaverage.config", "com.techgap.droolsaverage.exception" })
public class Main {

	@SuppressWarnings("unused")
	private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

	public static void main(String[] argv) {
		createTables();
		SpringApplication.run(Main.class, argv);
	}

	public static void createTables() {
		SingleConnectionDataSource ds = new SingleConnectionDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
//		ds.setUrl("jdbc:postgresql://localhost/droolsTestDB");
		ds.setUrl("jdbc:postgresql://db/droolsTestDB");
		ds.setUsername("postgres");
		ds.setPassword("postgres");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		System.out.println("Creating tables...");
		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS users (username TEXT NOT NULL PRIMARY KEY, password TEXT NOT NULL, enabled BOOLEAN NOT NULL);");
		
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS authorities (username TEXT NOT NULL PRIMARY KEY REFERENCES users(username), authority TEXT NOT NULL);");

		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS employee_metrics(employee_code SERIAL NOT NULL PRIMARY KEY, kpi1 DECIMAL NOT NULL, kpi2 DECIMAL "
						+ "			  NOT NULL, month INTEGER NOT NULL, year INTEGER NOT NULL, kpi3 DECIMAL NOT NULL, kpi4 DECIMAL NOT NULL, kpi_tot DECIMAL "
						+ "			  NOT NULL, name text, is_draft BOOLEAN);");

		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS rule_files (name TEXT NOT NULL PRIMARY KEY, location TEXT NOT NULL, month INTEGER NOT NULL, year INTEGER NOT NULL, rules TEXT NOT NULL, csv TEXT NOT NULL);");
		jdbcTemplate.execute(
				"INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$AXQmICgFYVflZicfkG2Qj.QKaq7HAS3Uvn7Ua9f5rv3tv5Kc0om2K', true)  ON CONFLICT (username) DO NOTHING;");
		jdbcTemplate.execute(
				"INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER') ON CONFLICT (username) DO NOTHING;");
		jdbcTemplate.execute(
				"INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN') ON CONFLICT (username) DO NOTHING;");
		System.out.println("... done creating tables destroying connection");
		ds.destroy();
	}
}
