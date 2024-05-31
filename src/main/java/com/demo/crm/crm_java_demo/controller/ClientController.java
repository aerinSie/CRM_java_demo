package com.demo.crm.crm_java_demo.controller;


import com.demo.crm.crm_java_demo.entity.ClientEntity;
import com.demo.crm.crm_java_demo.exception.ResourceNotFoundException;
import com.demo.crm.crm_java_demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    @Secured({"ROLE_SUPPER_USER", "ROLE_MANAGER", "ROLE_OPERATOR"})
    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_SUPPER_USER", "ROLE_MANAGER", "ROLE_OPERATOR"})
    public ClientEntity getClientById(@PathVariable Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    @PostMapping
    @Secured({"ROLE_SUPPER_USER", "ROLE_OPERATOR"})
    public ClientEntity createClient(@RequestBody ClientEntity client) {
        String operator = SecurityContextHolder.getContext().getAuthentication().getName();

        client.setCreatedBy(operator);
        client.setUpdatedBy(operator);
        return clientRepository.save(client);
    }

    @PostMapping("/bulk")
    @Secured({"ROLE_SUPPER_USER", "ROLE_OPERATOR"})
    public List<ClientEntity> createClients(@RequestBody List<ClientEntity> clients) {
        String operator = SecurityContextHolder.getContext().getAuthentication().getName();
        clients.forEach(client -> {
            client.setCreatedBy(operator);
            client.setUpdatedBy(operator);
        });
        return clientRepository.saveAll(clients);
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_SUPPER_USER", "ROLE_MANAGER"})
    public ClientEntity updateClient(@PathVariable Integer id, @RequestBody ClientEntity clientDetails) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        client.setName(clientDetails.getName());
        client.setEmail(clientDetails.getEmail());
        client.setPhone(clientDetails.getPhone());
        client.setCompanyId(clientDetails.getCompanyId());
        client.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        client.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_SUPPER_USER", "ROLE_MANAGER"})
    public void deleteClient(@PathVariable Integer id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        clientRepository.delete(client);
    }
}
