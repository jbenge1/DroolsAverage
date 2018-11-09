package com.techgap.droolsaverage.controller;

import com.techgap.droolsaverage.model.User;
import com.techgap.droolsaverage.model.UserRegistration;
import com.techgap.droolsaverage.util.AssetDAO;
import com.techgap.droolsaverage.util.RuleRunner;
import com.techgap.droolsaverage.util.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    public ModelAndView getEmployees(@RequestParam(required = false) String fileName1,
                                     @RequestParam(required = false) String fileName2,
                                     @RequestParam(required = false) String month,
                                     @RequestParam(required = false) String year) {
        ruleRunner.fireRulesHashMap2(Paths.get(UploadController.UPLOADED_FOLDER, fileName1), Paths.get(UploadController.UPLOADED_FOLDER, fileName2), month, year);
        assetDAO.addRuleFile(fileName2, UploadController.UPLOADED_FOLDER,fileName1, Integer.parseInt(month), Integer.parseInt(year));
        List<ArrayList<Object>> employees;
        employees = assetDAO.getEmployeesListAdmin(Integer.parseInt(month), Integer.parseInt(year));
        return new ModelAndView("employeesAdmin", "employee", employees);
    }
    
    @RequestMapping("ViewEmployees")
    public ModelAndView viewEmployees(@RequestParam(required = false) String month,
    								  @RequestParam(required = false) String year) {
    	List<ArrayList<Object>> employees;
    	employees = assetDAO.getEmployeesList(11, 2018);
    	return new ModelAndView("employees", "employee", employees);
    }
    
    @RequestMapping("ViewEmployeesAdmin")
    public ModelAndView viewEmployeesAdmin(@RequestParam(required = false) String month,
    								  	   @RequestParam(required = false) String year) {
    	List<ArrayList<Object>> employees;
    	employees = assetDAO.getEmployeesListAdmin(Integer.parseInt(month), Integer.parseInt(year));
    	return new ModelAndView("employeesAdmin", "employee", employees);
    }
    // ============================================================================================

    @GetMapping("/employeeAddForm")
    public String employeeAddForm() {
        return "employeeAddForm";
    }
    
    @GetMapping("/publishRankings")
    public String publishRankings() {
    	return "publishRankings";
    }
    
    @RequestMapping("/publish") 
    public ModelAndView publishEmployees(@RequestParam(required = false) String month,
		  	   					 		 @RequestParam(required = false) String year) {
    	assetDAO.publish(Integer.parseInt(month), Integer.parseInt(year));
    	List<ArrayList<Object>> employees;
    	employees = assetDAO.getEmployeesList(Integer.parseInt(month), Integer.parseInt(year));
    	return new ModelAndView("employees", "employee", employees);
    }

    //This seems like a terrible idea
    //hmmmm....
    @RequestMapping("/employeeAdd")
    public String employeeAdd(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        assetDAO.addUser(username, password);
        return "/newUser";
    }
    
    @GetMapping("/searchForRankings")
    public String searchForRankings() {
    	return "searchForRankings";
    }
    
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam("month") String month,
    						   @RequestParam("year") String year) {
    	List<ArrayList<Object>> employees;
        employees = assetDAO.getEmployeesList(Integer.parseInt(month), Integer.parseInt(year));
        return new ModelAndView("employees", "employee", employees);
    }
}
