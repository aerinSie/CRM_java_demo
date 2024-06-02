package com.demo.crm.crm_java_demo.controller;


import com.demo.crm.crm_java_demo.constant.SysUserRoles;
import com.demo.crm.crm_java_demo.entity.ClientEntity;
import com.demo.crm.crm_java_demo.req.ClientReq;
import com.demo.crm.crm_java_demo.res.ApiResponse;
import com.demo.crm.crm_java_demo.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_MANAGER, SysUserRoles.Fields.ROLE_OPERATOR})
    public ResponseEntity<ApiResponse<List<ClientEntity>>> getAllClients() {
        List<ClientEntity> list = clientService.getAllClients();
        return new ResponseEntity<>(new ApiResponse<>(list), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_MANAGER, SysUserRoles.Fields.ROLE_OPERATOR})
    public ResponseEntity<ApiResponse<ClientEntity>> getClientById(@PathVariable Long id) {
        ClientEntity result = clientService.getClientById(id);
        return new ResponseEntity<>(new ApiResponse<>(result), HttpStatus.OK);
    }

    @PostMapping
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_OPERATOR})
    public ResponseEntity<ApiResponse<String>> createClient(@RequestBody  @Valid ClientReq req) {
        try {
            clientService.createClient(req);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }

    @PostMapping("/bulk")
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_OPERATOR})
    public ResponseEntity<ApiResponse<String>> createClients(@RequestBody  @Valid List<ClientReq> reqs) {
        try {
            clientService.createClients(reqs);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_MANAGER})
    public ResponseEntity<ApiResponse<String>> updateClient(@PathVariable Long id, @RequestBody  @Valid ClientReq req) {
        try {
            clientService.updateClient(id, req);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_MANAGER})
    public ResponseEntity<ApiResponse<String>> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }
}
