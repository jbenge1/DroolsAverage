package com.techgap.droolsaverage.util;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AssetDAO {

	
	private String query;
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AssetDAO(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ArrayList<Object>> getEmployeesList(int month, int year) {
		query = "SELECT * FROM employee_metrics WHERE year = ? AND month = ? ORDER BY kpi_tot DESC;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(query, new Object[] {year, month});
		
		ArrayList<ArrayList<Object>> retval = new ArrayList<>();
		
		while(!list.isEmpty()) {
			Object[] temp = list.get(0).values().toArray();
			ArrayList<Object> tempEmp = new ArrayList<>();
			tempEmp.add((int)temp[0]);
			tempEmp.add((java.math.BigDecimal) temp[1]);
			tempEmp.add((java.math.BigDecimal) temp[2]);
			tempEmp.add((java.math.BigDecimal) temp[5]);
			tempEmp.add((java.math.BigDecimal) temp[6]);
			tempEmp.add((java.math.BigDecimal) temp[7]);
			tempEmp.add((String) temp[8]);
			
			retval.add(tempEmp);
			list.remove(0);
		}
		return retval;
	}
	
	/**
	 * 
	 * @param temp_arr
	 * @param month
	 * @param year
	 */
//	public void addEmployee(List<BigDecimal> temp_arr, int month, int year) {
//		Object[] temp = new Object[] {temp_arr.get(0), temp_arr.get(1), month, year, temp_arr.get(2), temp_arr.get(3), temp_arr.get(4)};
//		
//		query = "INSERT INTO employee_metrics (kpi1, kpi2, month, year, kpi3, kpi4, kpi_tot)values(?,?,?,?,?,?,?);";
//		
//		jdbcTemplate.update(query, temp);
//	}
	
	/**
	 * 
	 * @param temp_arr
	 * @param month
	 * @param year
	 * @param name
	 */
	public void addEmployee(List<BigDecimal> temp_arr, int month, int year, String name) {
		Object[] temp = new Object[] {temp_arr.get(0), temp_arr.get(1), month, year, temp_arr.get(2), temp_arr.get(3), temp_arr.get(4), name};
		
		query = "INSERT INTO employee_metrics (kpi1, kpi2, month, year, kpi3, kpi4, kpi_tot, name)VALUES(?,?,?,?,?,?,?,?);";
		
		jdbcTemplate.update(query, temp);
	}
	
	public void addEmployee(List<BigDecimal> temp_arr, int month, int year) {
		Object[] temp = new Object[] {temp_arr.get(temp_arr.size()-1), 0, month, year, 0, 0, 0};
		query = "INSERT INTO employee_metrics (kpi1, kpi2, month, year, kpi3, kpi4, kpi_tot)VALUES(?,?,?,?,?,?,?);";
		jdbcTemplate.update(query, temp);
	}
	
	/**
	 * 
	 * @param name
	 * @param location
	 * @param month
	 * @param year
	 */
	public void addRuleFile(String name, String location, int month, int year) {
		Object[] temp = new Object[] {name, location, month, year};
		
		query = "INSERT INTO rule_files (name, location, month, year) VALUES (?,?,?,?);";
		
		jdbcTemplate.update(query, temp);
	}
	
	public void addUser(String name, String password) {
		Object[] temp = new Object[] {name, passwordEncoder.encode(password), true};
		
		query = "INSERT INTO users (username, password, enabled) VALUES (?,?,?);";
		jdbcTemplate.update(query, temp);
		temp = null;
		
		temp = new Object[] {name, "ROLE_USER"};
		query = "INSERT INTO authorities (username, authority) values (?, ?);";
		jdbcTemplate.update(query, temp);
	}
}
