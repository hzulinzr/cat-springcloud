package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表ID：auth_user
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends BaseModel {

    /**
     * 表字段：auth_user.id 注释：用户表ID
     */
    private Long id;
    /**
     * 表字段：auth_user.update_time 注释：修改时间
     */
    private Long updateTime;
    /**
     * 表字段：auth_user.name 注释：用户名字
     */
    private String name;
    /**
     * 表字段：auth_user.address 注释：用户地址
     */
    private String address;
    /**
     * 表字段：auth_user.phone_number 注释：用户手机号码
     */
    private String phoneNumber;
    /**
     * 表字段：auth_user.client_id 注释：账号
     */
    private String clientId;
    /**
     * 表字段：auth_user.secret 注释：密码
     */
    private String secret;
}