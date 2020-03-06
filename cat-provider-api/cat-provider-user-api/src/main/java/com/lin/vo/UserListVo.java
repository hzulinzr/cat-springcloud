package com.lin.vo;

import lombok.Data;

/**
 * @author lzr
 * @date 2020-03-01 20:40:05
 */
@Data
public class UserListVo {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户头像
     */
    private String userUrl;
    /**
     * 创建时间
     */
    private Long createTime;
}
