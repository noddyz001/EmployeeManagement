package com.nip.springbootapplication.controller;

import com.nip.springbootapplication.model.Employee;
import com.nip.springbootapplication.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/login")
	public String login() {
		log.info("User login successfull");
		return "login";
	}

	@GetMapping("/")
	public String homePage() {
		log.info("User redirect to login");
		return "redirect:login";
	}

	@GetMapping("/process-login")
	public String viewHomePage(Model model) {
		try {
			model.addAttribute("listEmployees", employeeService.getAllEmployees());
			log.info("Successfully get employees data.");
		}catch (Exception e) {
			throw new RuntimeException("Error occured in getting employee data", e);
		}
		return "employees";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		try {
			employeeService.saveEmployee(employee);
			log.info("Successfully save employee detail.");
		} catch (Exception e){
			throw new RuntimeException("Error while saving Employee detail.");
		}
		return "redirect:/process-login";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
		Employee employee;
		try {
			employee = employeeService.getEmployeeById(id);
			log.info("Successfully get employees detail by Id.");
		} catch (Exception e){
			throw new RuntimeException("Error while getting employee detail by ID");
		}
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		try {
			this.employeeService.deleteEmployeeById(id);
			log.info("Successfully deleted the employee.");
		} catch (Exception e) {
			throw new RuntimeException("Error while deleting Employee.");
		}
		return "redirect:/process-login";
	}
}
