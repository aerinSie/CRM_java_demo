package com.demo.crm.crm_java_demo.repository;

import com.demo.crm.crm_java_demo.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysUserEntity, Long> {
}
