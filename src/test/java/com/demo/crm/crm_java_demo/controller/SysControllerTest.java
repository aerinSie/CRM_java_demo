package com.demo.crm.crm_java_demo.controller;

import com.demo.crm.crm_java_demo.filter.JwtRequestFilter;
import com.demo.crm.crm_java_demo.service.SysUserDetailsService;
import com.demo.crm.crm_java_demo.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SysController.class)
class SysControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private JwtRequestFilter jwtRequestFilter;
    @MockBean
    private SysUserDetailsService sysUserDetailsService;

    @Test
    @WithMockUser(roles = {"SUPPER_USER","MANAGER","OPERATOR"})
    void logout() throws Exception {
        mockMvc.perform(post("/logout").with(csrf()))
                .andExpect(status().isOk());
    }
}