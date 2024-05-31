package com.demo.crm.crm_java_demo.res;

import lombok.Data;

@Data
public class LoginRes {
    private String username;
    private String token;
    public LoginRes(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
