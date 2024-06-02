package com.demo.crm.crm_java_demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@ApiModel(description = "客戶類")
public class ClientReq {

    @ApiModelProperty(value = "公司ID", required = true)
    @DecimalMin("1")
    private Long companyId;

    @ApiModelProperty(value ="客戶名稱", required = true)
    @Size(min = 2, max = 20, message = "公司名稱長度介於 2-20")
    private String name;

    @ApiModelProperty(value ="客戶信箱", required = true)
    @Email
    private String email;

    @ApiModelProperty(value ="客戶電話", required = true)
    @Size(min = 3, max = 20, message = "電話長度介於 3-20")
    private String phone;


}
