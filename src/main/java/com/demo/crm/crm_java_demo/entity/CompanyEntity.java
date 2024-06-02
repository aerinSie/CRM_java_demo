package com.demo.crm.crm_java_demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "company")
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String createdBy;
    @Column
    private Timestamp createdAt;
    @Column
    private String updatedBy;
    @Column
    private Timestamp updatedAt;
}
