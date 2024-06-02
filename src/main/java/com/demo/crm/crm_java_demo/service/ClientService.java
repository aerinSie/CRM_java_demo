package com.demo.crm.crm_java_demo.service;

import com.demo.crm.crm_java_demo.entity.ClientEntity;
import com.demo.crm.crm_java_demo.exception.ResourceNotFoundException;
import com.demo.crm.crm_java_demo.repository.ClientRepository;
import com.demo.crm.crm_java_demo.req.ClientReq;
import org.apache.commons.lang3.text.ExtendedMessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

@Service
public class ClientService {


    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> getAllClients() {
        List<ClientEntity> list = clientRepository.findAll();

        return list;
    }

    public ClientEntity getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    public ClientEntity createClient(ClientReq req) throws Exception{
        String operator = SecurityContextHolder.getContext().getAuthentication().getName();
        ClientEntity client = new ClientEntity();
        BeanUtils.copyProperties(client, req);
        client.setCreatedBy(operator);
        client.setUpdatedBy(operator);
        return clientRepository.save(client);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ClientEntity> createClients(List<ClientReq> reqs) {
        String operator = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ClientEntity> clients =new ArrayList<>();
        reqs.forEach(req -> {
            ClientEntity client = new ClientEntity();
            try {
                BeanUtils.copyProperties(client, req);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            client.setCreatedBy(operator);
            client.setUpdatedBy(operator);
            clients.add(client);
        });
        return clientRepository.saveAll(clients);
    }

    @Transactional(rollbackFor = Exception.class)
    public ClientEntity updateClient(Long id, ClientReq req) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        client.setName(req.getName());
        client.setEmail(req.getEmail());
        client.setPhone(req.getPhone());
        client.setCompanyId(req.getCompanyId());
        client.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        client.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return clientRepository.save(client);
    }


    @Transactional(rollbackFor = Exception.class)
    public void deleteClient(Long id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        clientRepository.delete(client);
    }
}
