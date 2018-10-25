package com.techgap.DroolsAverage.DroolsAverage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserDAO(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}
	
	private String query;
	
	public void addUser(User user) {
		query = "insert into users values (?,?,?)";
		String query2 = "insert into authorities values (?,?)";
		jdbcTemplate.update(query, new Object[] {user.getName(), user.getPassword(),user.getRole()});
		jdbcTemplate.update(query2, new Object[] {user.getName(), "ROLE_USER"});
	}
}
