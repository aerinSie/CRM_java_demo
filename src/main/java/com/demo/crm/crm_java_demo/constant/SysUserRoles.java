package com.demo.crm.crm_java_demo.constant;

import lombok.experimental.FieldNameConstants;


@FieldNameConstants
public class SysUserRoles {
    /**
     * 用於 controller 權限設定
     */
    private String ROLE_SUPPER_USER, ROLE_MANAGER, ROLE_OPERATOR;
    /**
     * 用於 test 權限設定
     */
    private String SUPPER_USER, MANAGER, OPERATOR;
}
