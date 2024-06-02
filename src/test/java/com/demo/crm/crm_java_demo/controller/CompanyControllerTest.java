package com.demo.crm.crm_java_demo.controller;

import com.demo.crm.crm_java_demo.constant.SysUserRoles;
import com.demo.crm.crm_java_demo.entity.CompanyEntity;
import com.demo.crm.crm_java_demo.repository.CompanyRepository;
import com.demo.crm.crm_java_demo.service.CompanyService;
import com.demo.crm.crm_java_demo.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CompanyService companyService;

//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void getAllCompanies() throws Exception {
//        CompanyEntity company = new CompanyEntity();
//        company.setId(1L);
//        company.setName("Company 1");
//        when(companyService.getAllCompanies()).thenReturn(Collections.singletonList(company));
//
//        mockMvc.perform(get("/api/companies").contentType("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is("Company 1")));
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void getCompanyById() throws Exception {
//        CompanyEntity company = new CompanyEntity();
//        company.setId(1L);
//        company.setName("Company 1");
//        company.setAddress("12345");
//        when(companyService.getCompanyById(1L)).thenReturn(company);
//
//        mockMvc.perform(get("/api/companies/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Company 1")))
//                .andExpect(jsonPath("$.address", is("12345")));
//
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void createCompany() throws Exception {
//        CompanyEntity company = new CompanyEntity();
//        company.setId(1L);
//        company.setName("Company 1");
//        company.setAddress("123456");
//        when(companyService.createCompany(any(CompanyEntity.class))).thenReturn(company);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        mockMvc.perform(post("/api/companies")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(company)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Company 1")));
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void updateCompany() throws Exception {
//        CompanyEntity existingCompany = new CompanyEntity();
//        existingCompany.setId(1L);
//        existingCompany.setName("Company");
//        existingCompany.setAddress("000000");
//
//        CompanyEntity updatedCompany = new CompanyEntity();
//        updatedCompany.setId(1L);
//        updatedCompany.setName("Company Updated");
//        updatedCompany.setAddress("111111");
//
//        when(companyService.getCompanyById(anyLong())).thenReturn(existingCompany);
//        when(companyService.createCompany(any(CompanyEntity.class))).thenReturn(updatedCompany);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        mockMvc.perform(put("/api/companies/1")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedCompany)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Company Updated")))
//                .andExpect(jsonPath("$.address", is("111111")))
//                .andExpect(jsonPath("$.id", is(1)));
//
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void deleteCompany() throws Exception {
//        CompanyEntity existingCompany = new CompanyEntity();
//        existingCompany.setId(1L);
//        existingCompany.setName("company 1");
//
//        when(companyService.getCompanyById(anyLong())).thenReturn(existingCompany);
//
//        doNothing().when(companyService).deleteCompany(1L);
//
//        mockMvc.perform(delete("/api/companies/1").with(csrf()))
//                .andExpect(status().isOk());
//    }
}