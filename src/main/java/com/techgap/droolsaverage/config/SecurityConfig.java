package com.techgap.droolsaverage.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private static final String LOGIN = "/login";
	@Autowired
	DataSource dataSource;

	/**
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeRequests().antMatchers("/addEmployee").permitAll();
//		
		http.csrf().disable();
//		http.authorizeRequests().anyRequest().authenticated().and()
//			.formLogin().loginPage(LOGIN).permitAll()
//			.and().logout().permitAll().and();
		
	    http
        .authorizeRequests()
            .antMatchers("/employeeAddForm", "/employeeAdd").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage(LOGIN)
            .permitAll()
            .and()
        .logout()
            .permitAll();

		
		//everything else ADMIN and USERS can see
		http.authorizeRequests().antMatchers("/").hasAnyRole("ADMIN","USER")
				.and().formLogin().loginPage(LOGIN);	
		
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
}
