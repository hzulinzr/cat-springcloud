package com.lin.dto;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-14 19:51:52
 */
@Data
public class AuthUserUpdateDTO {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名字
     */
    private String name;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 用户手机号码
     */
    private String phoneNumber;
}
