package com.demo.crm.crm_java_demo.controller;


import com.demo.crm.crm_java_demo.entity.CompanyEntity;
import com.demo.crm.crm_java_demo.exception.ResourceNotFoundException;
import com.demo.crm.crm_java_demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    @Secured({"ROLE_SUPPER_USER", "ROLE_MANAGER", "ROLE_OPERATOR"})
    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_SUPPER_USER", "ROLE_MANAGER", "ROLE_OPERATOR"})
    public CompanyEntity getCompanyById(@PathVariable Integer id) {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    @PostMapping
    @Secured({"ROLE_SUPPER_USER", "ROLE_OPERATOR"})
    public CompanyEntity createCompany(@RequestBody CompanyEntity company) {
        String operator =SecurityContextHolder.getContext().getAuthentication().getName();
        company.setCreatedBy(operator);
        company.setUpdatedBy(operator);
        return companyRepository.save(company);
    }


    @PutMapping("/{id}")
    @Secured({"ROLE_SUPPER_USER", "ROLE_MANAGER"})
    public CompanyEntity updateCompany(@PathVariable Integer id, @RequestBody CompanyEntity companyDetails) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        company.setName(companyDetails.getName());
        company.setAddress(companyDetails.getName());
        company.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        company.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return companyRepository.save(company);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_SUPPER_USER", "ROLE_MANAGER"})
    public void deleteCompany(@PathVariable Integer id) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        companyRepository.delete(company);
    }
}
