package com.techgap.DroolsAverage.Model;

import org.springframework.stereotype.Component;

@Component
public class UserRegistration {
	
	private String userName;
	private String password;
	
	public UserRegistration(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public UserRegistration() {}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
