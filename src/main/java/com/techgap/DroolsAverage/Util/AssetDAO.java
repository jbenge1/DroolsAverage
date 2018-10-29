package com.techgap.DroolsAverage.Util;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//import com.techgap.DroolsAverage.Model.EmployeeClass;
import com.techgap.DroolsAverage.Model.EmployeeClass.Employee;



@Repository
public class AssetDAO {

	
	private String query;
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public AssetDAO(JdbcTemplate jdbcTemplate) throws InterruptedException {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<ArrayList<Object>> getEmployeesList(int month, int year) {
		query = "SELECT * FROM employee_metrics WHERE year = ? AND month = ? ORDER BY kpi_tot DESC;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(query, new Object[] {year, month});
		
		ArrayList<ArrayList<Object>> retval = new ArrayList<ArrayList<Object>>();
		
		while(!list.isEmpty()) {
			Object[] temp = list.get(0).values().toArray();
			ArrayList<Object> tempEmp = new ArrayList<Object>();
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
	
	
	public List<Employee> getEmployees(int month, int year) {
//		query = "SELECT * FROM employee_metrics WHERE year = 2018 AND month = 10 ORDER BY kpi_tot DESC;";
		query = "SELECT * FROM employee_metrics WHERE year = ? AND month = ? ORDER BY kpi_tot DESC;";
		
//		List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(query, new Object[] {year, month});
		ArrayList<Employee> retval = new ArrayList<>();
		//now lets loop through all the rows
		while(!list.isEmpty()) {
			//get each individual row
			Object[] temp = list.get(0).values().toArray();
			Employee tempEmp = new Employee();

			tempEmp.setCode((int)temp[0]);
			tempEmp.setPerformance1((java.math.BigDecimal) temp[1]);
			tempEmp.setPerformance2((java.math.BigDecimal) temp[2]);
			tempEmp.setPerformance3((java.math.BigDecimal) temp[5]);
			tempEmp.setPerformance4((java.math.BigDecimal) temp[6]);
			tempEmp.setPerformanceTotal((java.math.BigDecimal) temp[7]);
			
			//add it then remove it from the list
			retval.add(tempEmp);
			list.remove(0);
		}
		return retval;
	}
	
	/**
	 * 
	 * @param employee
	 * @param month
	 * @param year
	 */
	public void addEmployee(Employee employee, int month, int year) {
		query = "INSERT INTO employee_metrics (kpi1, kpi2, month, year, kpi3, kpi4, kpi_tot)values(?,?,?,?,?,?,?);";
		
		jdbcTemplate.update(query, new Object[] {employee.getPerformance1(),employee.getPerformance2(),month, year, 
												 employee.getPerformance3(),employee.getPerformance4(), employee.getPerformanceTotal(),});
	}
	
	/**
	 * 
	 * @param temp_arr
	 * @param month
	 * @param year
	 */
	public void addEmployee(ArrayList<BigDecimal> temp_arr, int month, int year) {
		Object[] temp = new Object[] {temp_arr.get(0), temp_arr.get(1), month, year, temp_arr.get(2), temp_arr.get(3), temp_arr.get(4)};
		
		query = "INSERT INTO employee_metrics (kpi1, kpi2, month, year, kpi3, kpi4, kpi_tot)values(?,?,?,?,?,?,?);";
		
		jdbcTemplate.update(query, temp);
	}
	
	/**
	 * 
	 * @param temp_arr
	 * @param month
	 * @param year
	 * @param name
	 */
	public void addEmployee(ArrayList<BigDecimal> temp_arr, int month, int year, String name) {
		Object[] temp = new Object[] {temp_arr.get(0), temp_arr.get(1), month, year, temp_arr.get(2), temp_arr.get(3), temp_arr.get(4), name};
		
		query = "INSERT INTO employee_metrics (kpi1, kpi2, month, year, kpi3, kpi4, kpi_tot, name)values(?,?,?,?,?,?,?,?);";
		
		jdbcTemplate.update(query, temp);
	}
}
