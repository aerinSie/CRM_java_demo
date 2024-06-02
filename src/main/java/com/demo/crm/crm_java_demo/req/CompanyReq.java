package com.demo.crm.crm_java_demo.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("公司類")
public class CompanyReq {

    @ApiModelProperty(value ="公司名稱", required = true)
    @Size(min = 2, max = 20, message = "公司名稱長度介於 2-20")
    private String name;

    @ApiModelProperty(value ="公司名稱", required = true)
    @Size(min = 2, max = 256, message = "公司地址長度介於 2-256")
    private String address;
}

