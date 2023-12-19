package com.jacaranda.employeeCompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public Page<Company> getCompanies(int pageNum, int pageSize){
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return companyRepository.findAll(pageable);
	}
	
	public void addCompany(Company company) {
		companyRepository.save(company);
	}
	
	public void deleteCompany(Company company) {
		companyRepository.delete(company);
	}
	
	public void editCompany(Company company) {
		addCompany(company);
		
	}
	
}
