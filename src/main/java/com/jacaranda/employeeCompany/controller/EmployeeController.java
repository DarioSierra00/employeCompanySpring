package com.jacaranda.employeeCompany.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.model.Employee;
import com.jacaranda.employeeCompany.service.CompanyService;
import com.jacaranda.employeeCompany.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/listEmployees")
	public String listEmployees(Model model,@RequestParam("pageNumber") Optional<String> idPage, @RequestParam("order") Optional<String> order,@RequestParam("asc") Optional<Boolean> asc,@RequestParam("keyword") Optional<String> keyword) {
		if(keyword != null) {
			List<Employee> listEmployees = employeeService.getEmployeesBySearch(keyword.orElse("firstName"));
			model.addAttribute("listEmployees", listEmployees);
		}
		else {
			Page<Employee> listEmployees = employeeService.getEmployees(idPage.orElse("0"),10,order.orElse("id"),asc.orElse(true));
			Integer totalItems = (int) listEmployees.getTotalElements();
			Integer totalPages = listEmployees.getTotalPages();
			Integer pageNumber = listEmployees.getNumber();	
			
			model.addAttribute("asc", asc.orElse(true));
			model.addAttribute("currentPage", pageNumber);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("totalItems", totalItems);
			model.addAttribute("listEmployees", listEmployees);
		}
		return "/employee/listEmployees";
	}
	
	@GetMapping("/elements")
	public String searchEmployee(Model model,@RequestParam("pageNumber") Optional<String> idPage, @RequestParam("order") Optional<String> order,@RequestParam("asc") Optional<Boolean> asc) {
		Page<Employee> listEmployees = employeeService.getEmployeesBySearch(keyword);
		Integer totalItems = (int) listEmployees.getTotalElements();
		Integer totalPages = listEmployees.getTotalPages();
		Integer pageNumber = listEmployees.getNumber();	
		
		model.addAttribute("asc", asc.orElse(true));
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listEmployees", listEmployees);
		return "/employee/listEmployees";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(Model model,@RequestParam("pageNumber") Optional<String> idPage, @RequestParam("order") Optional<String> order,@RequestParam("asc") Optional<Boolean> asc) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		Page<Company> companies = companyService.getCompanies(idPage.orElse("1"), 11,order.orElse("1"),asc.orElse(false));
		model.addAttribute("companies", companies);

		return "/employee/addEmployee";
	}
	
	@GetMapping("/saveEmployee")
	public String saveEmployee(Model model,@ModelAttribute("employee") Employee employeeSave) {
		employeeService.save(employeeSave);
		model.addAttribute("add", "Empleado añadido con éxito");
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		return "/employee/addEmployee";
	}
	
	@GetMapping("/editEmployee")
	public String editEmployee(Model model,@RequestParam("id") Employee employee,@RequestParam("pageNumber") Optional<String> idPage, @RequestParam("order") Optional<String> order,@RequestParam("asc") Optional<Boolean> asc) {
		model.addAttribute("employee", employee);
		Page<Company> companies = companyService.getCompanies(idPage.orElse("1"), 11,order.orElse("1"),asc.orElse(false));
		model.addAttribute("companies", companies);
		
		return "/employee/editEmployee";
	}
	
	@GetMapping("editEmployeeConfirm")
	public String editEmployeeConfirm(Model model,@ModelAttribute("employee") Employee employeeEdit,@RequestParam("pageNumber") Optional<String> idPage, @RequestParam("order") Optional<String> order,@RequestParam("asc") Optional<Boolean> asc) {
		model.addAttribute("employee", employeeEdit);
		employeeService.edit(employeeEdit);
		model.addAttribute("edit","Empleado editado correctamente");
		Page<Company> companies = companyService.getCompanies(idPage.orElse("1"), 11,order.orElse("1"),asc.orElse(false));
		model.addAttribute("companies", companies);
		
		return "/employee/editEmployee";
	}
	
}
