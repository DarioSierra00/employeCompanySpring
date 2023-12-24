package com.jacaranda.employeeCompany.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.employeeCompany.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public Page<Employee> findByFirstName(String name,Pageable pageable);
}
