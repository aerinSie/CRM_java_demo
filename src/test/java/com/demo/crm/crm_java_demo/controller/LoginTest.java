package com.demo.crm.crm_java_demo.controller;

import com.demo.crm.crm_java_demo.req.LoginReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginTest {

    @BeforeEach
    public void setUp() {
        RestAssured.port = 12345;
    }

//    @Test
//    void login() throws Exception {
//        LoginReq loginReq = new LoginReq();
//        loginReq.setUsername("user1");
//        loginReq.setPassword("user1");
//        ObjectMapper objectMapper = new ObjectMapper();
//        given()
//                .contentType(ContentType.JSON)
//                .body(objectMapper.writeValueAsString(loginReq))
//                .when()
//                .post("/login")
//                .then()
//                .statusCode(200)
//        ;
//    }
}
