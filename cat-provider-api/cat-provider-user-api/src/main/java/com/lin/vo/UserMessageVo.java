package com.lin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lzr
 * @date 2020-12-16 10:57:53
 */
@Data
public class UserMessageVo implements Serializable {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户注册时间
     */
    private Long createTime;
    /**
     * 用户账号
     */
    private String username;
}
