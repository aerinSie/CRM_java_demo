package com.demo.crm.crm_java_demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "sys_user")
@Getter
@Setter
public class SysUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String role;

    @Column
    private String createdBy;
    @Column
    private Timestamp createdAt;
    @Column
    private String updatedBy;
    @Column
    private Timestamp updatedAt;
}