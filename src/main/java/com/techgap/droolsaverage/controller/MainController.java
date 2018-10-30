package com.techgap.droolsaverage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.techgap.droolsaverage.model.User;
import com.techgap.droolsaverage.model.UserRegistration;
import com.techgap.droolsaverage.util.AssetDAO;
import com.techgap.droolsaverage.util.RuleRunner;
import com.techgap.droolsaverage.util.UserDAO;

@Controller
public class MainController {

	@Autowired
	private AssetDAO assetDAO;

	@Autowired
	private RuleRunner ruleRunner;
	// ===========================================

	private BCryptPasswordEncoder bCryptPassword = new BCryptPasswordEncoder();

	@Autowired
	private UserDAO userDAO;
	
	@ModelAttribute("user")
	public UserRegistration setUpUserForm() {
		return new UserRegistration();
	}

	@GetMapping("register")
	public String addUserForm() {
		return "register";
	}

	@PostMapping("addUser")
	public String addUser(@ModelAttribute("user") UserRegistration user) {
		String pass = bCryptPassword.encode(user.getPassword());
		User newUser = new User(user.getUserName(), pass, 1);
		userDAO.addUser(newUser);
		return "newUser";
	}

	@GetMapping("temp")
	public String temp() {
		return "temp";
	}
	// ===========================================

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Username or Password incorrect");

		if (logout != null)
			model.addAttribute("msg", "Logged Out");

		return "login";
	}
	
	@RequestMapping("Employees")
	public ModelAndView getEmployees(@RequestParam(required = false)String fileName1,
									 @RequestParam(required = false)String fileName2,
									 @RequestParam(required = false)String month, 
									 @RequestParam(required = false)String year) {
		
		ruleRunner.fireRulesHashMap(fileName1, fileName2, month, year);
		assetDAO.addRuleFile(fileName2, System.getProperty("java.io.tmpdir"), Integer.parseInt(month), Integer.parseInt(year));
		List<ArrayList<Object>> employees;
		employees = assetDAO.getEmployeesList(Integer.parseInt(month), Integer.parseInt(year));
		return new ModelAndView("employees", "employee", employees);
	}

	// ============================================================================================
	
	@GetMapping("/employeeAddForm")
	public String employeeAddForm() {
		return "employeeAddForm";
	}
	
	
	//This is a terrible idea
	//hmmmm....
	@RequestMapping("/employeeAdd")
	public String employeeAdd(@RequestParam("username")String username,
							  @RequestParam("password")String password) {
		assetDAO.addUser(username, password);
		return "/newUser";
	}
	
	
	@GetMapping("/selectEmployees")
	public String selectEmployees(){
		return "selectEmployee";
	}
	
	
	@RequestMapping("/selection")
	public ModelAndView select(@RequestParam("month")String month,
							   @RequestParam("year")String year) {
		List<ArrayList<Object>> employees;
		employees = assetDAO.getEmployeesList(Integer.parseInt(month), Integer.parseInt(year));
		return new ModelAndView("employees", "employee", employees);
	}
	
}
