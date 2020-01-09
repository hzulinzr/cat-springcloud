package com.lin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ：user
 * @author hzh 
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    /**
     * 表字段：user.id 注释：
     */
    private Long id;
    /**
     * 表字段：user.username 注释：
     */
    private String username;
    /**
     * 表字段：user.password 注释：
     */
    private String password;
    /**
     * 表字段：user.user_info_id 注释：
     */
    private Long userInfoId;
    /**
     * 表字段：user.uuid 注释：
     */
    private String uuid;
    /**
     * 表字段：user.token 注释：
     */
    private String token;
    /**
     * 表字段：user.register_time 注释：
     */
    private Long registerTime;
    /**
     * 表字段：user.last_login_time 注释：
     */
    private Long lastLoginTime;
    /**
     * 表字段：user.is_login 注释：
     */
    private Integer isLogin;
}