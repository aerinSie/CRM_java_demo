package com.demo.crm.crm_java_demo.controller;


import com.demo.crm.crm_java_demo.constant.SysUserRoles;
import com.demo.crm.crm_java_demo.entity.CompanyEntity;
import com.demo.crm.crm_java_demo.req.CompanyReq;
import com.demo.crm.crm_java_demo.res.ApiResponse;
import com.demo.crm.crm_java_demo.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Companies")
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ApiOperation(value = "查公司列表", notes = "獲取當前所有列表")
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_MANAGER, SysUserRoles.Fields.ROLE_OPERATOR})
    public ResponseEntity<ApiResponse<List<CompanyEntity>>> getAllCompanies() {
        List<CompanyEntity> list = companyService.getAllCompanies();
        return new ResponseEntity<>(new ApiResponse<>(list), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查單一公司", notes = "輸入指定公司ID即回傳公司資料")
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_MANAGER, SysUserRoles.Fields.ROLE_OPERATOR})
    public ResponseEntity<ApiResponse<CompanyEntity>> getCompanyById(@PathVariable Long id) {
        CompanyEntity result = companyService.getCompanyById(id);
        return new ResponseEntity<>(new ApiResponse<>(result), HttpStatus.OK);
    }

    @PostMapping
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_OPERATOR})
    public ResponseEntity<ApiResponse<String>> createCompany(@RequestBody  @Valid CompanyReq req) {
        try {
            companyService.createCompany(req);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_MANAGER})
    public ResponseEntity<ApiResponse<String>> updateCompany(@PathVariable Long id, @RequestBody  @Valid CompanyReq req) {
        try {
            companyService.updateCompany(id, req);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured({SysUserRoles.Fields.ROLE_SUPPER_USER, SysUserRoles.Fields.ROLE_MANAGER})
    public ResponseEntity<ApiResponse<String>> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompany(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ApiResponse<>("ok"), HttpStatus.OK);
    }
}
