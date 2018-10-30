package com.techgap.droolsaverage.model;

import org.springframework.stereotype.Component;

@Component
public class User {

	private String name, password;
	private int role;
	
	public User(String name, String password, int role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public User() {}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getRole() {
		return this.role;
	}
}
