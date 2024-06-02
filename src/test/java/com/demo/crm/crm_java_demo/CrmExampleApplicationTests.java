package com.demo.crm.crm_java_demo;

import com.demo.crm.crm_java_demo.entity.ClientEntity;
import com.demo.crm.crm_java_demo.entity.CompanyEntity;
import com.demo.crm.crm_java_demo.repository.ClientRepository;
import com.demo.crm.crm_java_demo.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CrmExampleApplicationTests {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreateCompany() {
        CompanyEntity company = new CompanyEntity();
        company.setName("Test Company");
        company.setAddress("Test Address");

        CompanyEntity savedCompany = companyRepository.save(company);

        assertThat(savedCompany).isNotNull();
        assertThat(savedCompany.getId()).isNotNull();
    }

    @Test
    public void testCreateClient() {
        ClientEntity client = new ClientEntity();
        client.setName("Test Client");
        client.setEmail("test@client.com");
        client.setCompanyId(1L);

        ClientEntity savedClient = clientRepository.save(client);

        assertThat(savedClient).isNotNull();
        assertThat(savedClient.getId()).isNotNull();
    }

    @Test
    public void testGetAllCompanies() {
        List<CompanyEntity> companies = companyRepository.findAll();
        assertThat(companies).isNotEmpty();
    }

    @Test
    public void testGetAllClients() {
        List<ClientEntity> clients = clientRepository.findAll();
        assertThat(clients).isNotEmpty();
    }
}
