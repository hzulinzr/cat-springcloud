package com.lin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lzr
 * @date 2019-11-25 15:14:55
 */
@Data
public class UserDTO {
    /**
     * uuid 用户uuid，身份标识
     */
    @NotBlank(message = "uuid not null", groups = {Logout.class})
    private String uuid;
    /**
     * 表字段：user.username 注释：
     */
    @NotBlank(message = "username not null", groups = {Install.class})
    private String username;
    /**
     * 表字段：user.password 注释：
     */
    @NotBlank(message = "password not null", groups = {Install.class})
    private String password;
    public interface Install{

    }
    public interface Logout{

    }
}
