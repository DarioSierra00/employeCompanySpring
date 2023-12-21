package com.jacaranda.employeeCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.employeeCompany.model.CompanyProject;
import com.jacaranda.employeeCompany.model.CompanyProjectId;

public interface CompanyProjectRepository extends JpaRepository<CompanyProject, CompanyProjectId>{

}
