package com.demo.crm.crm_java_demo.controller;

import com.demo.crm.crm_java_demo.constant.SysUserRoles;
import com.demo.crm.crm_java_demo.entity.ClientEntity;
import com.demo.crm.crm_java_demo.service.ClientService;
import com.demo.crm.crm_java_demo.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private ClientService clientService;

    @MockBean
    private SysUserRoles sysUserRoles;

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
    void getClients() throws Exception {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setName("Client 1");
        when(clientService.getAllClients()).thenReturn(Collections.singletonList(client));

        mockMvc.perform(get("/api/clients").contentType("application/json"))
                .andExpect(status().isOk())
        ;
    }


    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
    void getClientById() throws Exception {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setName("Client 1");
        client.setEmail("client1@example.com");
        when(clientService.getClientById(1L)).thenReturn(client);

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER, SysUserRoles.Fields.MANAGER, SysUserRoles.Fields.OPERATOR})
    void createClient() throws Exception {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
        client.setPhone("00000000");
        client.setCompanyId(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/clients")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
    void createClients() throws Exception {
        ClientEntity client1 = new ClientEntity();
        client1.setId(1L);
        client1.setName("John Doe1");
        client1.setEmail("john.doe1@example.com");
        client1.setPhone("00000000");
        client1.setCompanyId(1L);
        ClientEntity client2 = new ClientEntity();
        client2.setId(2L);
        client2.setName("Jane Doe2");
        client2.setEmail("jane.doe2@example.com");
        client2.setPhone("000000002");
        client2.setCompanyId(1L);
        List<ClientEntity> clients = Arrays.asList(client1, client2);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/clients/bulk")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clients)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
    void updateClient() throws Exception {
        ClientEntity updatedClient = new ClientEntity();
        updatedClient.setId(1L);
        updatedClient.setName("John Doe Updated");
        updatedClient.setEmail("john.doe.updated@example.com");
        updatedClient.setPhone("123456789");
        updatedClient.setCompanyId(2L);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/api/clients/1")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER, SysUserRoles.Fields.MANAGER})
    void deleteClientBySupperUser() throws Exception {
        mockMvc.perform(delete("/api/clients/1").with(csrf()))
                .andExpect(status().isOk());
    }

}