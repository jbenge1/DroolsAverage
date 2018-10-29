package com.techgap.DroolsAverage.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

import com.techgap.DroolsAverage.Model.EmployeeClass;
import com.techgap.DroolsAverage.Model.User;
import com.techgap.DroolsAverage.Model.UserRegistration;
import com.techgap.DroolsAverage.Model.EmployeeClass.Employee;
import com.techgap.DroolsAverage.Util.AssetDAO;
import com.techgap.DroolsAverage.Util.RuleRunner;
import com.techgap.DroolsAverage.Util.UserDAO;

@Controller
public class MainController {

	{
		System.out.println("\nHERE\n");
	}

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
		System.out.println("HERE");
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Username or Password incorrect");

		if (logout != null)
			model.addAttribute("msg", "Logged Out");

		return "login";
	}

//	@RequestMapping("Employees")
//	public ModelAndView getEmployees(@RequestParam(required = false)String fileName1,
//									 @RequestParam(required = false)String fileName2,
//									 @RequestParam(required = false)String month, 
//									 @RequestParam(required = false)String year) {
//		
//		ruleRunner.fireRulesHashMap(fileName1, fileName2, month, year);
//		List<Employee> employees = new ArrayList<Employee>();
//		employees = assetDAO.getEmployees(Integer.parseInt(month), Integer.parseInt(year));
//		return new ModelAndView("employees", "employee", employees);
//	}
	
	@RequestMapping("Employees")
	public ModelAndView getEmployees(@RequestParam(required = false)String fileName1,
									 @RequestParam(required = false)String fileName2,
									 @RequestParam(required = false)String month, 
									 @RequestParam(required = false)String year) {
		
		ruleRunner.fireRulesHashMap(fileName1, fileName2, month, year);
		List<ArrayList<Object>> employees = new ArrayList<ArrayList<Object>>();
		employees = assetDAO.getEmployeesList(Integer.parseInt(month), Integer.parseInt(year));
		return new ModelAndView("employees", "employee", employees);
	}

//    @ModelAttribute("employee")
//    public Employee setUpEmployeeForm() {
//    	return new Employee();
//    }
//    
//    //This is a normal mapping to the addEmployeeForm.jsp
//    @GetMapping("EmployeeAddForm")
//    public String addEmployeeForm() {
//    	return "addEmployeeForm";
//    }
//    //Here is where the fun happens
//    @PostMapping("addEmployee") 
//    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee, Model model) {
//    	ruleRunner.runRules(employee);
//    	assetDAO.addEmployee(employee);
//
//    	List<Employee> list = new ArrayList<>();
//        list = assetDAO.getEmployees();
//        return new ModelAndView("employees", "employee", list);
//    	
//    }
	// ============================================================================================
	
	@GetMapping("/selectEmployees")
	public String selectEmployees(){
		return "selectEmployee";
	}
	
	
	@RequestMapping("/selection")
	public ModelAndView select(@RequestParam("month")String month,
							   @RequestParam("year")String year) {
		List<ArrayList<Object>> employees = new ArrayList<ArrayList<Object>>();
		employees = assetDAO.getEmployeesList(Integer.parseInt(month), Integer.parseInt(year));
		return new ModelAndView("employees", "employee", employees);
	}
	
}
