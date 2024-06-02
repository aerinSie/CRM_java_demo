package com.demo.crm.crm_java_demo.service;

import com.demo.crm.crm_java_demo.entity.CompanyEntity;
import com.demo.crm.crm_java_demo.exception.ResourceNotFoundException;
import com.demo.crm.crm_java_demo.repository.CompanyRepository;
import com.demo.crm.crm_java_demo.req.CompanyReq;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.findAll();
    }

    public CompanyEntity getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    public CompanyEntity createCompany(CompanyReq req)throws Exception {
        String operator = SecurityContextHolder.getContext().getAuthentication().getName();
        CompanyEntity company = new CompanyEntity();
        BeanUtils.copyProperties(company, req);
        company.setCreatedBy(operator);
        company.setUpdatedBy(operator);
        return companyRepository.save(company);
    }


    @Transactional(rollbackFor = Exception.class)
    public CompanyEntity updateCompany(Long id, CompanyReq req) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        company.setName(req.getName());
        company.setAddress(req.getName());
        company.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        company.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return companyRepository.save(company);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCompany(Long id) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        companyRepository.delete(company);
    }
}
