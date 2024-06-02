package com.demo.crm.crm_java_demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "client")
@Getter
@Setter
@ApiModel("ClientEntity")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long companyId;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;

    @Column
    private String createdBy;
    @Column
    private Timestamp createdAt;
    @Column
    private String updatedBy;
    @Column
    private Timestamp updatedAt;


}
