package com.demo.crm.crm_java_demo.controller;

import com.demo.crm.crm_java_demo.constant.SysUserRoles;
import com.demo.crm.crm_java_demo.entity.CompanyEntity;
import com.demo.crm.crm_java_demo.req.CompanyReq;
import com.demo.crm.crm_java_demo.service.CompanyService;
import com.demo.crm.crm_java_demo.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CompanyService companyService;

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
    void getAllCompanies() throws Exception {
        CompanyEntity company = new CompanyEntity();
        company.setId(1L);
        company.setName("Company 1");
        when(companyService.getAllCompanies()).thenReturn(Collections.singletonList(company));

        mockMvc.perform(get("/api/companies").contentType("application/json"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is("Company 1")))
        ;
    }

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
    void getCompanyById() throws Exception {
        CompanyEntity company = new CompanyEntity();
        company.setId(1L);
        company.setName("Company 1");
        company.setAddress("12345");
        when(companyService.getCompanyById(1L)).thenReturn(company);

        mockMvc.perform(get("/api/companies/1"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Company 1")))
//                .andExpect(jsonPath("$.address", is("12345")))
        ;

    }

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
    void createCompany() throws Exception {
        CompanyEntity company = new CompanyEntity();
        company.setId(1L);
        company.setName("Company 1");
        company.setAddress("123456");
        when(companyService.createCompany(any(CompanyReq.class))).thenReturn(company);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/companies")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
    void updateCompany() throws Exception {
        CompanyEntity updatedCompany = new CompanyEntity();
        updatedCompany.setName("Company Updated");
        updatedCompany.setAddress("111111");

        when(companyService.createCompany(any(CompanyReq.class))).thenReturn(updatedCompany);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/companies/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCompany)))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER, SysUserRoles.Fields.MANAGER})
    void deleteCompany() throws Exception {
        mockMvc.perform(delete("/api/companies/1").with(csrf()))
                .andExpect(status().isOk());
    }
}