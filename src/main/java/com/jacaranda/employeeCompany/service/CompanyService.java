package com.jacaranda.employeeCompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeCompany.model.Company;
import com.jacaranda.employeeCompany.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	public Page<Company> getCompanies(String pageNum, int pageSize,String order,Boolean asc){
		Pageable pageable = null;
		try {
			Integer pageNumInt = Integer.valueOf(pageNum);
			if(asc) {
				pageable = PageRequest.of(pageNumInt, pageSize,Sort.by(order).ascending());
			}
			else {
				pageable = PageRequest.of(pageNumInt, pageSize,Sort.by(order).descending());
			}
			
		} catch (Exception e) {
			pageable = PageRequest.of(1, pageSize,Sort.by(order).ascending());
		}
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
