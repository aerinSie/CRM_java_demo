package com.demo.crm.crm_java_demo.repository;

import com.demo.crm.crm_java_demo.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Integer> {
}
