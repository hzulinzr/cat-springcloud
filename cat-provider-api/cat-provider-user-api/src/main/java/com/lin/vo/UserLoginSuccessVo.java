package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-01-11 15:18:51
 */
@Data
public class UserLoginSuccessVo {
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录返回的token
     */
    private String accessToken;
    /**
     * 权限范围
     */
    private String scope;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户头像
     */
    private String userUrl;
}
