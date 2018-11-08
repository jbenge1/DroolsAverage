package com.techgap.droolsaverage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


@Repository
public class AssetDAO {


    private String query;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AssetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ArrayList<Object>> getEmployeesList(int month, int year) {
    	query = "SELECT is_draft FROM employee_metrics;";
    	List<Map<String,Object>> temp_bool = jdbcTemplate.queryForList(query);
    	try {
    		if((boolean)temp_bool.get(0).values().toArray()[0]) {
    			return new ArrayList<ArrayList<Object>>();
    		}
    	}catch(ArrayIndexOutOfBoundsException e) {return new ArrayList<ArrayList<Object>>();}
    	
        query = "SELECT * FROM employee_metrics WHERE year = ? AND month = ? ORDER BY kpi_tot DESC;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(query, year, month);

        ArrayList<ArrayList<Object>> retval = new ArrayList<>();

        while (!list.isEmpty()) {
            Object[] temp = list.get(0).values().toArray();
            ArrayList<Object> tempEmp = new ArrayList<>();
            tempEmp.add(temp[0]);
            tempEmp.add(temp[1]);
            tempEmp.add(temp[2]);
            tempEmp.add(temp[5]);
            tempEmp.add(temp[6]);
            tempEmp.add(temp[7]);
            tempEmp.add(temp[8]);

            retval.add(tempEmp);
            list.remove(0);
        }
        return retval;
    }

    public void publish(int month, int year) {
    	System.err.println("HERE");
    	query = "UPDATE employee_metrics SET is_draft = false WHERE year = ? AND month = ?;";
    	jdbcTemplate.update(query, new Object[] {year, month});
    	
    }
    /**
     * 
     * @param month
     * @param year
     * @return
     */
    public List<ArrayList<Object>> getEmployeesListAdmin(int month, int year) {   
        query = "SELECT * FROM employee_metrics WHERE year = ? AND month = ? ORDER BY kpi_tot DESC;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(query, year, month);

        ArrayList<ArrayList<Object>> retval = new ArrayList<>();

        while (!list.isEmpty()) {
            Object[] temp = list.get(0).values().toArray();
            ArrayList<Object> tempEmp = new ArrayList<>();
            tempEmp.add(temp[0]);
            tempEmp.add(temp[1]);
            tempEmp.add(temp[2]);
            tempEmp.add(temp[5]);
            tempEmp.add(temp[6]);
            tempEmp.add(temp[7]);
            tempEmp.add(temp[8]);

            retval.add(tempEmp);
            list.remove(0);
        }
        return retval;
    }

    /**
     * @param kpis
     * @param month
     * @param year
     * @param name
     */
    public void addEmployee(List<Double> kpis, int month, int year, String name) {
        Object[] temp = new Object[]{kpis.get(0), kpis.get(1), month, year, kpis.get(2), kpis.get(3), kpis.get(4), name};

        query = "INSERT INTO employee_metrics (kpi1, kpi2, month, year, kpi3, kpi4, kpi_tot, name, is_draft)VALUES(?,?,?,?,?,?,?,?,true);";

        jdbcTemplate.update(query, temp);
    }

    /**
     * @param name
     * @param location
     * @param month
     * @param year
     */
    public void addRuleFile(String nameRules, String location,String nameCsv, int month, int year) {
    	String rules = "";
    	String csv = "";
    	try {
    		rules = new String(Files.readAllBytes(Paths.get(location + nameRules)));
    		csv   = new String(Files.readAllBytes(Paths.get(location + nameCsv)));
    	}catch(IOException ne) {System.err.println("File not found");}
    	
    	Object[] temp = new Object[]{nameRules, location, month, year, rules, csv};

        query = "INSERT INTO rule_files (name, location, month, year, rules, csv) VALUES (?,?,?,?,?,?);";

        jdbcTemplate.update(query, temp);
    }

    public void addUser(String name, String password) {
        Object[] temp = new Object[]{name, passwordEncoder.encode(password), true};

        query = "INSERT INTO users (username, password, enabled) VALUES (?,?,?);";
        jdbcTemplate.update(query, temp);
        temp = null;

        temp = new Object[]{name, "ROLE_USER"};
        query = "INSERT INTO authorities (username, authority) values (?, ?);";
        jdbcTemplate.update(query, temp);
    }
}
