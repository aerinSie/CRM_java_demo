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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void getClients() throws Exception {
//        ClientEntity client = new ClientEntity();
//        client.setId(1L);
//        client.setName("Client 1");
//        when(clientService.getAllClients()).thenReturn(Collections.singletonList(client));
//
//        mockMvc.perform(get("/api/clients").contentType("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is("Client 1")));
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void getClientById() throws Exception {
//        ClientEntity client = new ClientEntity();
//        client.setId(1L);
//        client.setName("Client 1");
//        client.setEmail("client1@example.com");
//        when(clientService.getClientById(1L)).thenReturn(client);
//
//        mockMvc.perform(get("/api/clients/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Client 1")))
//                .andExpect(jsonPath("$.email", is("client1@example.com")));
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void createClient() throws Exception {
//        ClientEntity client = new ClientEntity();
//        client.setId(1L);
//        client.setName("John Doe");
//        client.setEmail("john.doe@example.com");
//        client.setPhone("00000000");
//        client.setCompanyId(1L);
////        when(clientRepository.save(any(ClientEntity.class))).thenReturn(client);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        mockMvc.perform(post("/api/clients")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(client)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("John Doe")));
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void createClients() throws Exception {
//        ClientEntity client1 = new ClientEntity();
//        client1.setId(1L);
//        client1.setName("John Doe1");
//        client1.setEmail("john.doe1@example.com");
//        client1.setPhone("00000000");
//        client1.setCompanyId(1L);
//        ClientEntity client2 = new ClientEntity();
//        client2.setId(2L);
//        client2.setName("Jane Doe2");
//        client2.setEmail("jane.doe2@example.com");
//        client2.setPhone("000000002");
//        client2.setCompanyId(1L);
//        List<ClientEntity> clients = Arrays.asList(client1, client2);
//
////        when(clientRepository.saveAll(anyList())).thenReturn(clients);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        mockMvc.perform(post("/api/clients/bulk")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(clients)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is("John Doe1")))
//                .andExpect(jsonPath("$[0].email", is("john.doe1@example.com")))
//                .andExpect(jsonPath("$[1].name", is("Jane Doe2")))
//                .andExpect(jsonPath("$[1].email", is("jane.doe2@example.com")));
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void updateClient() throws Exception {
//        ClientEntity existingClient = new ClientEntity();
//        existingClient.setId(1L);
//        existingClient.setName("John Doe");
//        existingClient.setEmail("john.doe@example.com");
//
//        ClientEntity updatedClient = new ClientEntity();
//        updatedClient.setId(1L);
//        updatedClient.setName("John Doe Updated");
//        updatedClient.setEmail("john.doe.updated@example.com");
//        updatedClient.setPhone("123456789");
//        updatedClient.setCompanyId(2L);
//
//        when(clientService.getClientById(1L)).thenReturn(existingClient);
//        when(clientService.createClient(existingClient)).thenReturn(updatedClient);
//        when(clientService.updateClient(1L,updatedClient)).thenReturn(updatedClient);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        mockMvc.perform(put("/api/clients/1")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedClient)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("John Doe Updated")))
//                .andExpect(jsonPath("$.email", is("john.doe.updated@example.com")))
//                .andExpect(jsonPath("$.phone", is("123456789")))
//                .andExpect(jsonPath("$.companyId", is(2)));
//    }
//
//    @Test
//    @WithMockUser(roles = {SysUserRoles.Fields.SUPPER_USER})
//    void deleteClient() throws Exception {
//        ClientEntity existingClient = new ClientEntity();
//        existingClient.setId(1L);
//        existingClient.setName("John Doe");
//        when(clientService.getClientById(1L)).thenReturn(existingClient);
//
//        mockMvc.perform(delete("/api/clients/1").with(csrf()))
//                .andExpect(status().isOk());
//    }
}