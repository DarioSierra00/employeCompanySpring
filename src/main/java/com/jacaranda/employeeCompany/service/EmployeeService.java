package com.jacaranda.employeeCompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Employee;
import com.jacaranda.employeeCompany.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Page<Employee> getEmployees(String pageNum, int pageSize,String sortField,Boolean asc){
		Pageable pageable = null;
		try {
			Integer pageNumInt = Integer.valueOf(pageNum);
			if(asc) {
				pageable = PageRequest.of(pageNumInt, pageSize,Sort.by(sortField).ascending());
				
			}else {
				pageable = PageRequest.of(pageNumInt, pageSize,Sort.by(sortField).descending());		
			}
		
			
			}catch (Exception e) {
			pageable = PageRequest.of(1, pageSize);
		}
		return employeeRepository.findAll(pageable);
	}
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void edit(Employee employee) {
		save(employee);
	}
	
}
