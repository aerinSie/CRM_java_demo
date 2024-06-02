package com.demo.crm.crm_java_demo.req;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginReq {
    @Size(min = 2, max = 20, message = "用戶名稱長度介於 2-20")
    private String username;
    @Size(min = 4, max = 20, message = "密碼長度介於 4-20")
    private String password;
}
